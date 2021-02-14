package app.healios.test.di.fragments

import app.healios.test.ui.posts.list.PostListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface PostListFragmentSubComponent : AndroidInjector<PostListFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PostListFragment>
}


