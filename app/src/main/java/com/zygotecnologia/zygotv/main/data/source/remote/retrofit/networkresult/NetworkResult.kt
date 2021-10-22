package com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult

/**
 * A simple class for distinguishing successful network calls from any kind of failure.
 */
sealed class NetworkResult<out T> {

    data class Success<out T>(val data: T): NetworkResult<T>()

    object Failure: NetworkResult<Nothing>()
}
