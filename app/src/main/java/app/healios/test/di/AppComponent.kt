package app.healios.test.di

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import app.healios.test.App
import app.healios.test.di.posts.PostDetailsFragmentModule
import app.healios.test.di.posts.PostListFragmentModule
import app.healios.test.ui.posts.details.PostDetailsViewModel
import app.healios.test.ui.posts.list.PostListViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        RetrofitModule::class,
        CacheModule::class,
        PostListFragmentModule::class,
        PostDetailsFragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    fun injectApp(app: App)
    fun injectPostListViewModel(viewModel: PostListViewModel)
    fun injectPostDetailsViewModel(viewModel: PostDetailsViewModel)

    override fun inject(instance: App?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}