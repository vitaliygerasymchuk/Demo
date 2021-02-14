package app.healios.test.data.repository.impl

import androidx.lifecycle.LiveData
import app.healios.test.data.api.Api
import app.healios.test.data.api.ApiResponse
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.NetworkBoundResource
import app.healios.test.data.cache.PostsDao
import app.healios.test.data.model.Post
import app.healios.test.data.repository.PostsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostsRepositoryImpl @Inject constructor(
    override val api: Api,
    override val cache: PostsDao,
    override val executors: AppExecutors,
) : PostsRepository(api, cache, executors) {

    private val postListResource =
        object : NetworkBoundResource<List<Post>, List<Post>>(executors) {
            override fun saveCallResult(item: List<Post>) {
                log("getPosts: saving ${item.size}")
                cache.insertAll(item)
            }

            override fun shouldFetch(data: List<Post>?): Boolean {
                log("getPosts: shouldFetch ${data.isNullOrEmpty()}")
                return data.isNullOrEmpty()
            }

            override fun loadFromDb(): LiveData<List<Post>> {
                log("getPosts: loadFromDb")
                return cache.getAll()
            }

            override fun createCall(): LiveData<ApiResponse<List<Post>>> {
                log("getPosts: createCall")
                return api.getPosts()
            }
        }

    override fun getPosts() = postListResource.asLiveData()

    override fun refresh() {
        executors.diskIO().execute {
            cache.clear()
            executors.mainThread().execute {
                postListResource.refresh()
            }
        }
    }
}