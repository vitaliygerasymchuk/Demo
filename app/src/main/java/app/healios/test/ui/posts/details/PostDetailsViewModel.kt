package app.healios.test.ui.posts.details

import android.app.Application
import androidx.lifecycle.LiveData
import app.healios.test.abs.AbsAndroidDaggerViewModel
import app.healios.test.data.api.Resource
import app.healios.test.data.model.Comment
import app.healios.test.data.model.Post
import app.healios.test.data.model.User
import app.healios.test.data.repository.impl.CommentsRepositoryImpl
import app.healios.test.data.repository.impl.UsersRepositoryImpl
import javax.inject.Inject

class PostDetailsViewModel(application: Application) : AbsAndroidDaggerViewModel(application) {

    init {
        getDaggerComponent().injectPostDetailsViewModel(this)
    }

    @Inject
    lateinit var usersRepository: UsersRepositoryImpl

    @Inject
    lateinit var commentsRepository: CommentsRepositoryImpl

    var post: Post? = null

    fun getUser(): LiveData<Resource<User?>> {
        return usersRepository.getUserById(post?.userId ?: 0)
    }

    fun getComments():LiveData<Resource<List<Comment>>>{
        return commentsRepository.getCommentsByPostId(post?.userId ?: 0)
    }
}