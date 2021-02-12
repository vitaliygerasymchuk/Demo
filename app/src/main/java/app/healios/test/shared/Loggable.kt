package app.healios.test.shared

import android.util.Log
import app.healios.test.BuildConfig

interface Loggable {
    fun log(msg: String) {
        if (isLoggable()) {
            Log.d(this::class.simpleName, msgWithThreadId(msg))
        }
    }


    private fun msgWithThreadId(msg: String): String {
        return "[${Thread.currentThread().id}]: $msg"
    }

    private fun isLoggable(): Boolean {
        return BuildConfig.DEBUG
    }
}