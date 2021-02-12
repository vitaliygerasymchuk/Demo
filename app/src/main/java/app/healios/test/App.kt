package app.healios.test

import android.app.Application
import app.healios.test.di.AppComponent
import app.healios.test.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject


class App : Application(), HasAndroidInjector {

    lateinit var daggerComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        daggerComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        daggerComponent.injectApp(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
}