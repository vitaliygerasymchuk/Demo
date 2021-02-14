package app.healios.test

import app.healios.test.data.api.AppExecutors
import java.util.concurrent.Executor

class TestExecutors : AppExecutors(executor, executor, executor) {
    companion object {
        val executor = Executor { it.run() }
    }
}