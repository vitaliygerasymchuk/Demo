package app.healios.test.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import app.healios.test.data.model.Post

@Dao
interface PostsDao : BaseDao<Post> {
    @Query("SELECT * FROM posts")
    fun getAll(): LiveData<List<Post>>

    @Query("DELETE FROM posts")
    fun clear()
}