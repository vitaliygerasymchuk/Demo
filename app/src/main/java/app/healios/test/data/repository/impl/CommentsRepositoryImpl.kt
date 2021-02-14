package app.healios.test.data.repository.impl

import androidx.lifecycle.LiveData
import app.healios.test.data.api.*
import app.healios.test.data.cache.CommentsDao
import app.healios.test.data.model.Comment
import app.healios.test.data.repository.CommentsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsRepositoryImpl @Inject constructor(
    override val api: Api,
    override val cache: CommentsDao,
    override val executors: AppExecutors,
) : CommentsRepository(api, cache, executors) {

    override fun getCommentsByPostId(postId: Long) =
        object : NetworkBoundResource<List<Comment>, List<Comment>>(executors) {
            override fun saveCallResult(item: List<Comment>) {
                cache.insertAll(item)
            }

            override fun shouldFetch(data: List<Comment>?): Boolean {
                return data.isNullOrEmpty()
            }

            override fun loadFromDb(): LiveData<List<Comment>> {
                return cache.getAllByPostId(postId)
            }

            override fun createCall(): LiveData<ApiResponse<List<Comment>>> {
                return api.getCommentsByPostId(postId)
            }
        }.asLiveData()
}