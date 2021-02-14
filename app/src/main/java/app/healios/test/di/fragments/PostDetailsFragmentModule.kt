package app.healios.test.di.fragments

import app.healios.test.ui.posts.details.PostDetailsFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [PostDetailsFragmentSubComponent::class])
abstract class PostDetailsFragmentModule {
    @Binds
    @IntoMap
    @ClassKey(PostDetailsFragment::class)
    abstract fun bindPostDetailsFragmentInjectorFactory(factory: PostDetailsFragmentSubComponent.Factory): AndroidInjector.Factory<*>
}