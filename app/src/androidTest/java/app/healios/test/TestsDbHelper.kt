package app.healios.test

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.healios.test.data.cache.AppDatabase
import app.healios.test.data.cache.CommentsDao
import app.healios.test.data.cache.PostsDao
import app.healios.test.data.cache.UsersDao

object TestsDbHelper {
    private var database: AppDatabase? = null

    fun getPostsDao(): PostsDao {
        return getDb().postsDao()
    }

    fun getCommentsDao(): CommentsDao {
        return getDb().commentsDao()
    }

    fun getUsersDao(): UsersDao {
        return getDb().usersDao()
    }

    fun close(){
        getDb().close()
        database = null
    }

    private fun getDb(): AppDatabase {
        if (database == null) {
            database = Room.inMemoryDatabaseBuilder(
                ApplicationProvider.getApplicationContext(), AppDatabase::class.java
            ).build()
        }
        return database!!
    }
}