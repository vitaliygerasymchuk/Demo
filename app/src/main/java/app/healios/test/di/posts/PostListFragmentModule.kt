package app.healios.test.di.posts

import app.healios.test.ui.posts.list.PostListFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [PostListFragmentSubComponent::class])
abstract class PostListFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(PostListFragment::class)
    abstract fun bindPostListFragmentInjectorFactory(factory: PostListFragmentSubComponent.Factory): AndroidInjector.Factory<*>
}