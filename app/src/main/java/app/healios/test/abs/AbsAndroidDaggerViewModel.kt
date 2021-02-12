package app.healios.test.abs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.healios.test.App
import app.healios.test.di.AppComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class AbsAndroidDaggerViewModel(private val app: Application) : AndroidViewModel(app) {

    fun getDaggerComponent(): AppComponent {
        return (app as App).daggerComponent
    }

    protected fun background(function: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            function()
        }
    }
}