package app.healios.test.ui.abs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.healios.test.App
import app.healios.test.di.AppComponent

abstract class AbsAndroidDaggerViewModel(private val app: Application) : AndroidViewModel(app) {

    fun getDaggerComponent(): AppComponent {
        return (app as App).daggerComponent
    }
}