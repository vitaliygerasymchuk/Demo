package app.healios.test.data.repository.impl

import androidx.lifecycle.LiveData
import app.healios.test.data.api.*
import app.healios.test.data.cache.UsersDao
import app.healios.test.data.model.User
import app.healios.test.data.repository.UsersRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepositoryImpl @Inject constructor(
    override val api: Api,
    override val cache: UsersDao,
    override val executors: AppExecutors,
) : UsersRepository(api, cache, executors) {
    override fun getUserById(userId: Long) =
        object : NetworkBoundResource<User?, User>(executors) {
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