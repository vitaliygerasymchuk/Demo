package app.healios.test.data.api

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    companion object {
        fun <T> loading(data: T? = null): Resource<T> {
            return Loading(data)
        }

        fun <T> success(data: T): Resource<T> {
            return Success(data)
        }

        fun <T> error(message: String, data: T?):Resource<T> {
            return Error(message, data)
        }
    }
}