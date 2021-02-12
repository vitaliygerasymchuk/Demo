package app.healios.test.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import app.healios.test.data.model.Comment

@Dao
interface CommentsDao : BaseDao<Comment> {
    @Query("SELECT * FROM comments WHERE postId =:postId")
    fun getAllByPostId(postId: Long): LiveData<List<Comment>>
}