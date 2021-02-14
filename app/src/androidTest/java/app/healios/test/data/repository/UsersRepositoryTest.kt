package app.healios.test.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import app.healios.test.TestsDbHelper
import app.healios.test.data.api.Resource
import app.healios.test.data.model.User
import app.healios.test.data.repository.impl.UsersRepositoryImpl
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersRepositoryTest : BaseRepositoryTest<User, UsersRepository>() {

    @Before
    fun before() {
        repository = UsersRepositoryImpl(
            api,
            TestsDbHelper.getUsersDao(),
            executors
        )
    }

    @Test
    fun testGetUserById() {
        var finished = false
        val liveDataResults = arrayListOf<Resource<User?>>()
        repository.getUserById(1).observeForever {
            finished = it is Resource.Success || it is Resource.Error
            liveDataResults.add(it)
        }
        do {
            Thread.sleep(200)
        } while (!finished)
        assertResourceSuccess(liveDataResults = liveDataResults)
    }
}