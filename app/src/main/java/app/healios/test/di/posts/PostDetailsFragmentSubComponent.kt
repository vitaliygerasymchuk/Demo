package app.healios.test.di.posts

import app.healios.test.ui.posts.details.PostDetailsFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface PostDetailsFragmentSubComponent : AndroidInjector<PostDetailsFragment> {
    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<PostDetailsFragment>
}