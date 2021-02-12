package app.healios.test.di

import android.app.Application
import app.healios.test.data.cache.AppDatabase
import app.healios.test.data.cache.CommentsDao
import app.healios.test.data.cache.PostsDao
import app.healios.test.data.cache.UsersDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule() {
    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDatabase {
        return AppDatabase.instance(application)
    }

    @Singleton
    @Provides
    fun providesPostsDao(database: AppDatabase): PostsDao {
        return database.postsDao()
    }

    @Singleton
    @Provides
    fun providesUsersDao(database: AppDatabase): UsersDao {
        return database.usersDao()
    }

    @Singleton
    @Provides
    fun providesCommentsDao(database: AppDatabase): CommentsDao {
        return database.commentsDao()
    }
}