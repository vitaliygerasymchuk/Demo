package app.healios.test.ui.posts.list

import android.app.Application
import app.healios.test.ui.abs.AbsAndroidDaggerViewModel
import app.healios.test.data.repository.impl.PostsRepositoryImpl
import app.healios.test.shared.Loggable
import javax.inject.Inject

class PostListViewModel(application: Application) : AbsAndroidDaggerViewModel(application),
    Loggable {

    init {
        getDaggerComponent().injectPostListViewModel(this)
    }

    @Inject
    lateinit var postsRepository: PostsRepositoryImpl

    val posts = postsRepository.getPosts()

    fun refresh() {
        postsRepository.refresh()
    }
}