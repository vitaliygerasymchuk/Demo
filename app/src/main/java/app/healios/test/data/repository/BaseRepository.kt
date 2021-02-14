package app.healios.test.data.repository

import app.healios.test.data.api.Api
import app.healios.test.data.api.AppExecutors
import app.healios.test.data.cache.BaseDao
import app.healios.test.shared.Loggable

abstract class BaseRepository<T>(
    open val api: Api,
    open val cache: BaseDao<T>,
    open val executors: AppExecutors
) : Loggable {
    open fun refresh() {}
}