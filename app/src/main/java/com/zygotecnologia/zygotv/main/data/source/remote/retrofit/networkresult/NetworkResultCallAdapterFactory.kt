package com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * CallAdapter factory used by retrofit to create a NetworkResultCallAdapter when necessary.
 *
 * The return type should be of type Call<NetworkResult<R>>, with R being the expected response from
 * the server in the case of success.
 */
class NetworkResultCallAdapterFactory : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResult::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val resultType = getParameterUpperBound(0, responseType)
        return NetworkResultCallAdapter<Any>(resultType)
    }
}