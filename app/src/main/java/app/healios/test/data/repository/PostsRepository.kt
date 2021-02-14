package app.healios.test.data.repository

import androidx.lifecycle.LiveData
import app.healios.test.data.api.Api
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.Resource
import app.healios.test.data.cache.BaseDao
import app.healios.test.data.model.Post

abstract class PostsRepository(
    override val api: Api,
    override val cache: BaseDao<Post>,
    override val executors: AppExecutors
) : BaseRepository<Post>(api, cache, executors) {
    abstract fun getPosts(): LiveData<Resource<List<Post>>>
}