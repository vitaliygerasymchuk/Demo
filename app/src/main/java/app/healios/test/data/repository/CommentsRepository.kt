package app.healios.test.data.repository

import androidx.lifecycle.LiveData
import app.healios.test.data.api.Api
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.Resource
import app.healios.test.data.cache.BaseDao
import app.healios.test.data.model.Comment
import app.healios.test.data.model.Post

abstract class CommentsRepository(
    override val api: Api,
    override val cache: BaseDao<Comment>,
    override val executors: AppExecutors
) : BaseRepository<Comment>(api, cache, executors) {
    abstract fun getCommentsByPostId(postId: Long): LiveData<Resource<List<Comment>>>
}