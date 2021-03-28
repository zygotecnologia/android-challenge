package com.zygotecnologia.zygotv.model.network

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T): NetworkResult<T>()
    data class GenericError(val code: Int? = null): NetworkResult<Nothing>()
    object NetworkError: NetworkResult<Nothing>()
}
