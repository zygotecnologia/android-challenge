package com.zygotecnologia.zygotv.common

import com.zygotecnologia.zygotv.uistate.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.Response

inline fun <reified T> Resource<T>.asFlow(): Flow<Resource<T>> {
    return flowOf(this)
}

 inline fun < reified T> safeCall(responseCall: () -> Response<T>): Resource<T> {
    val result =  try {
        val response = responseCall.invoke()
        val responseMessage = response.message()
        if (response.isSuccessful) {
            response.body()
        } else {
            responseMessage
        }
    } catch (e: Exception) {
        throw e
    }
     return result.asResource()
}

inline fun <reified T> Any?.asResource(): Resource<T> {
    return when(this){
        is T ->{
            Resource.Success(data = this as T)
        }
        else -> {
            Resource.Failed(this as String)
        }
    }
}
