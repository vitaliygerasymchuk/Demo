package app.healios.test.ui.posts.list

import android.app.Application
import app.healios.test.abs.AbsAndroidDaggerViewModel
import app.healios.test.data.repository.PostsRepository
import app.healios.test.shared.Loggable
import javax.inject.Inject

class PostListViewModel(application: Application) : AbsAndroidDaggerViewModel(application),
    Loggable {

    init {
        getDaggerComponent().injectPostListViewModel(this)
    }

    @Inject
    lateinit var postsRepository: PostsRepository

    val posts = postsRepository.getPosts()

    fun refresh() {
        postsRepository.refresh()
    }
}