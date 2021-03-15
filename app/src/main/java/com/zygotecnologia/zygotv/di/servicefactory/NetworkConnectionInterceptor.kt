package com.zygotecnologia.zygotv.di.servicefactory

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.zygotecnologia.zygotv.utils.NoNetworkingException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


/**
 * @author allef.santos on 15/03/21
 */

class NetworkConnectionInterceptor(context: Context) : Interceptor {
    private val mContext: Context = context

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NoNetworkingException
        }
        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as
                    ConnectivityManager

        return if (android.os.Build.VERSION.SDK_INT >=
            android.os.Build.VERSION_CODES.M
        ) {
            postAndroidMInternetCheck(connectivityManager)
        } else {
            preAndroidMInternetCheck(connectivityManager)
        }
    }

    private fun preAndroidMInternetCheck(
        connectivityManager: ConnectivityManager
    ): Boolean {
        val activeNetwork = connectivityManager.activeNetworkInfo
        if (activeNetwork != null) {
            return (activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                    activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
        }
        return false
    }

    private fun postAndroidMInternetCheck(
        connectivityManager: ConnectivityManager
    ): Boolean {
        val network = connectivityManager.activeNetwork
        val connection =
            connectivityManager.getNetworkCapabilities(network)

        return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }

}