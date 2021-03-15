package com.zygotecnologia.zygotv.common

import com.zygotecnologia.zygotv.common.uistate.Resource
import com.zygotecnologia.zygotv.common.uistate.State
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.utils.NoNetworkingException
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
    } catch (e: NoNetworkingException) {
        e.message
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

 inline fun < reified T> Any?.asState(): State<T> {
    return when(this){
        is T ->{
            State.success(this)
        }
        else -> {
            State.error(this as String)
        }
    }

}

 fun ShowResponse.toState(): State<ShowResponse> {
    return State.success(this)

}

 fun <E> List<E>.toState(): State<List<E>> {
    return State.success(this)
}
