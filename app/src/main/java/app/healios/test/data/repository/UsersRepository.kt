package app.healios.test.data.repository

import androidx.lifecycle.LiveData
import app.healios.test.data.api.Api
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.Resource
import app.healios.test.data.cache.BaseDao
import app.healios.test.data.model.User

abstract class UsersRepository(
    override val api: Api,
    override val cache: BaseDao<User>,
    override val executors: AppExecutors
) : BaseRepository<User>(api, cache, executors) {
    abstract fun getUserById(userId: Long): LiveData<Resource<User?>>
}