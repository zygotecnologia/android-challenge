package com.zygotecnologia.zygotv.common.uistate

sealed class State<T> {
    class Loading<T> : State<T>()

    data class Success<T>(val data: T) : State<T>()

    data class Error<T>(val message: String) : State<T>()

    fun isLoading(): Boolean = this is Loading

    fun isSuccessful(): Boolean = this is Success

    fun isFailed(): Boolean = this is Error

    companion object {

        /**
         * Returns [State.Loading] instance.
         */
        fun <T> loading() = Loading<T>()

        /**
         * Returns [State.Success] instance.
         * @param data Data to emit with status.
         */
        fun <T> success(data: T) =
            Success(data)

        /**
         * Returns [State.Error] instance.
         * @param message Description of failure.
         */
        fun <T> error(message: String) =
            Error<T>(message)

        /**
         * Returns [State] from [Resource]
         */
        fun <T> fromResource(resource: Resource<T>): State<T> = when (resource) {
            is Resource.Success -> resource.data?.let { success(it) } ?: error("")
            is Resource.Failed -> resource.errorMessage?.let { error(it) } ?: error("")
            else -> error("")
        }



        fun <T> getData(resource: Resource<T>): T? = when (resource) {
            is Resource.Success -> resource.data
            else -> null
        }

    }
}