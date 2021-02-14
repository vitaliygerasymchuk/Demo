package app.healios.test.di

import app.healios.test.data.api.Api
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.cache.CommentsDao
import app.healios.test.data.cache.PostsDao
import app.healios.test.data.cache.UsersDao
import app.healios.test.data.repository.CommentsRepository
import app.healios.test.data.repository.PostsRepository
import app.healios.test.data.repository.UsersRepository
import app.healios.test.data.repository.impl.CommentsRepositoryImpl
import app.healios.test.data.repository.impl.PostsRepositoryImpl
import app.healios.test.data.repository.impl.UsersRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {
    @Singleton
    @Provides
    fun providesPostsRepository(
        api: Api,
        cache: PostsDao,
        executors: AppExecutors
    ): PostsRepository {
        return PostsRepositoryImpl(api, cache, executors)
    }

    @Singleton
    @Provides
    fun providesUsersRepository(
        api: Api,
        cache: UsersDao,
        executors: AppExecutors
    ): UsersRepository {
        return UsersRepositoryImpl(api, cache, executors)
    }

    @Singleton
    @Provides
    fun providesCommentsRepository(
        api: Api,
        cache: CommentsDao,
        executors: AppExecutors
    ): CommentsRepository {
        return CommentsRepositoryImpl(api, cache, executors)
    }
}