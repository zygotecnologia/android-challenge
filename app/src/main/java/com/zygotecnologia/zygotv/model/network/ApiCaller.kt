package com.zygotecnologia.zygotv.model.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class ApiCaller {

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): NetworkResult<T> {
        return withContext(dispatcher) {
            try {
                NetworkResult.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> NetworkResult.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        NetworkResult.GenericError(code)
                    }
                    else -> NetworkResult.GenericError(null)
                }
            }
        }
    }

}