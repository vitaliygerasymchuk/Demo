package app.healios.test.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.healios.test.TestsDbHelper
import app.healios.test.data.api.Resource
import app.healios.test.data.model.Comment
import app.healios.test.data.repository.impl.CommentsRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentsRepositoryTest : BaseRepositoryTest<Comment, CommentsRepository>() {

    @Before
    fun before() {
        repository = CommentsRepositoryImpl(
            api,
            TestsDbHelper.getCommentsDao(),
            executors
        )
    }

    @Test
    fun testGetCommentsByPostId() {
        var isFinished = false
        val liveDataResults = arrayListOf<Resource<List<Comment>>>()
        repository.getCommentsByPostId(22).observeForever {
            isFinished = it is Resource.Success || it is Resource.Error
            liveDataResults.add(it)
        }
        do {
            Thread.sleep(200)
        } while (!isFinished)
        assertResourceSuccess(liveDataResults)
    }
}