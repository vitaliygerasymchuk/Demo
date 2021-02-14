package app.healios.test.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.healios.test.TestExecutors
import app.healios.test.TestsDbHelper
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.api.Resource
import app.healios.test.di.RetrofitModule
import org.junit.After
import org.junit.Rule
import java.io.IOException

open class BaseRepositoryTest<D, T : BaseRepository<D>> {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val executors: AppExecutors = TestExecutors()
    protected val api = RetrofitModule().providesApi()
    protected lateinit var repository: T


    @After
    @Throws(IOException::class)
    open fun after() {
        TestsDbHelper.close()
    }

    protected fun <V> assertResourceSuccess(liveDataResults: ArrayList<Resource<V>>) {
        assert(liveDataResults.size == 2)
        assert(liveDataResults.first() is Resource.Loading)
        assert(liveDataResults.last() is Resource.Success)
        if (liveDataResults.last().data is List<*>) {
            assert(!(liveDataResults.last().data as List<*>).isNullOrEmpty())
        } else {
            assert(liveDataResults.last().data != null)
        }
    }
}