package app.healios.test.data.repository

import androidx.lifecycle.LiveData
import app.healios.test.data.api.*
import app.healios.test.data.cache.UsersDao
import app.healios.test.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(
    val api: Api,
    val cache: UsersDao,
    val executors: AppExecutors,
) : BaseRepository() {

    fun getUser(userId: Long) = object : NetworkBoundResource<User?, User>(executors) {
        override fun saveCallResult(item: User) {
            cache.insert(item)
        }

        override fun shouldFetch(data: User?): Boolean {
            return data == null
        }

        override fun loadFromDb(): LiveData<User?> {
            return cache.getById(userId)
        }

        override fun createCall(): LiveData<ApiResponse<User>> {
            return api.getUserById(userId)
        }
    }.asLiveData()
}