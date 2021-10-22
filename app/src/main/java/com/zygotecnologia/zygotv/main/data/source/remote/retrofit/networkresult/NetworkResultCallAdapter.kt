package com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult

import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

/**
 * Adapter to convert a Call<R> to a NetworkResultCall<T>.
 */
class NetworkResultCallAdapter<R>(
    private val successType: Type
) : CallAdapter<R, Call<NetworkResult<R>>> {

    override fun adapt(call: Call<R>): Call<NetworkResult<R>> = NetworkResultCall(call, successType)

    override fun responseType(): Type = successType
}