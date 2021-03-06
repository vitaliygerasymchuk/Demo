package app.healios.test.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.healios.test.TestsDbHelper
import app.healios.test.data.api.Resource
import app.healios.test.data.model.Post
import app.healios.test.data.repository.impl.PostsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostsRepositoryTest : BaseRepositoryTest<Post, PostsRepository>() {
    @Before
    fun before() {
        repository = PostsRepositoryImpl(
            api,
            TestsDbHelper.getPostsDao(),
            executors
        )
    }

    @Test
    fun testGetPosts() {
        var finished = false
        val liveDataResults = arrayListOf<Resource<List<Post>>>()
        repository.getPosts().observeForever {
            finished = it is Resource.Success || it is Resource.Error
            liveDataResults.add(it)
        }
        do {
            Thread.sleep(200)
        } while (!finished)
        assertResourceSuccess(liveDataResults = liveDataResults)
    }
}