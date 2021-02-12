package app.healios.test.data.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import app.healios.test.data.model.User

@Dao
interface UsersDao : BaseDao<User> {
    @Query("SELECT * FROM users WHERE id =:id LIMIT 1")
    fun getById(id: Long): LiveData<User?>
}