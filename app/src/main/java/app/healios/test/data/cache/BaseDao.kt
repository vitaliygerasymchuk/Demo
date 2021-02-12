package app.healios.test.data.cache

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Insert
    fun insertAll(t: List<T>)

    @Update
    fun update(t: T)
}