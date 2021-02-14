package app.healios.test.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import app.healios.test.BuildConfig
import app.healios.test.data.model.Comment
import app.healios.test.data.model.Post
import app.healios.test.data.model.User

private const val DB_VERSION = 1

@Database(
    version = DB_VERSION,
    exportSchema = false,
    entities = [
        User::class,
        Post::class,
        Comment::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao
    abstract fun usersDao(): UsersDao
    abstract fun commentsDao(): CommentsDao

    companion object {
        private var instance: AppDatabase? = null
        fun instance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "${BuildConfig.APPLICATION_ID}_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}