package app.healios.test.data.repository

import androidx.lifecycle.LiveData
import app.healios.test.data.api.Api
import app.healios.test.data.api.ApiResponse
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.NetworkBoundResource
import app.healios.test.data.cache.CommentsDao
import app.healios.test.data.model.Comment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommentsRepository @Inject constructor(
    val api: Api,
    val cache: CommentsDao,
    val executors: AppExecutors,
) : BaseRepository() {
    fun getCommentsByPostId(postId: Long) =
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