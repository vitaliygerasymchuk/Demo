package app.healios.test.data.api

import androidx.lifecycle.LiveData
import app.healios.test.data.model.Comment
import app.healios.test.data.model.Post
import app.healios.test.data.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Api {
    @GET("/posts/")
    fun getPosts(): LiveData<ApiResponse<List<Post>>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") userId: Long): LiveData<ApiResponse<User>>

    @GET("comments")
    fun getCommentsByPostId(@Query("postId") postId: Long): LiveData<ApiResponse<List<Comment>>>

    companion object {
        const val BAS_URL = "http://jsonplaceholder.typicode.com"
    }
}

fun <T> ApiResponse<T>.toLiveData() {

}