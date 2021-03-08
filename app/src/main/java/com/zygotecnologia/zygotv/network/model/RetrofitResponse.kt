package com.zygotecnologia.zygotv.network.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.squareup.moshi.JsonEncodingException
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

class RetrofitResponse<T>(
    private val context: Context?,
    private val request: suspend () -> Response<T>
) : ApiResponse<T> {

    override suspend fun result(): Resource<T> {
        return try {
            val response = request.invoke()
            val data = response.body()

            if (response.isSuccessful && data != null) {
                success(data)
            } else {
                error(response.code(), response.errorBody())
            }
        } catch (exception: Exception) {
            failure(exception)
        }
    }

    override fun success(data: T) = Resource.success(data)

    override fun error(code: Int, errorBody: ResponseBody?): Resource<T> {
        return Resource.error(genericError)
    }

    override fun failure(exception: Exception): Resource<T> {
        return when (exception) {
            is JsonEncodingException -> {
                Resource.error(genericError)
            }
            is UnknownHostException, is IOException -> {
                Resource.error(verifyInternetConnection())
            }
            else -> {
                Resource.error(genericError)
            }
        }
    }

    private fun verifyInternetConnection(): RequestError {
        return if(context != null) {
            if (hasInternetConnection(context)) {
                genericError
            } else {
                connectionError
            }
        }else{
            genericError
        }
    }

    companion object {

        val connectionError: RequestError
            get() = RequestError(
                code = 401,
                title = "Connection error",
                message = "There is a connection error, check your internet connection"
            )

        val genericError : RequestError
            get() = RequestError(
                code = 400,
                title = "ops!",
                message = "something get wrong. check your internet connection and try again :)",
            )

        private fun hasInternetConnection(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                val network = connectivityManager.activeNetwork
                val capabilities = connectivityManager.getNetworkCapabilities(network)
                capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                ))
            } else {
                true
            }
        }
    }
}