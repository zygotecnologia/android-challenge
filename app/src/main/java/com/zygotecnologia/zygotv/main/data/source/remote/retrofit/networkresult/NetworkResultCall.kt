package com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult

import okhttp3.Request
import okio.Timeout
import retrofit2.*
import java.lang.reflect.Type

/**
 * Call class used with retrofit to handle NetworkResult as a valid call type.
 *
 * NetworkResultCall does not support the execute() operation.
 */
class NetworkResultCall<R>(
    private val delegate: Call<R>,
    private val successType: Type
) : Call<NetworkResult<R>> {

    override fun enqueue(callback: Callback<NetworkResult<R>>) = delegate.enqueue(
        object : Callback<R> {

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(this@NetworkResultCall, Response.success(response.toEither()))
            }

            private fun Response<R>.toEither(): NetworkResult<R> {
                if (!isSuccessful) {
                    return NetworkResult.Failure
                }

                body()?.let { body -> return NetworkResult.Success(body) }

                return NetworkResult.Failure
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                callback.onResponse(this@NetworkResultCall, Response.success(NetworkResult.Failure))
            }
        }
    )

    override fun clone(): Call<NetworkResult<R>> = NetworkResultCall(delegate.clone(), successType)

    override fun execute(): Response<NetworkResult<R>> {
        throw UnsupportedOperationException("NetworkResponseCall doesn't support execute")
    }

    override fun isExecuted() = delegate.isExecuted

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled() = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}




