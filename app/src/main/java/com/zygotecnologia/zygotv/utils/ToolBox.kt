package com.zygotecnologia.zygotv.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

fun testConnection(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo: NetworkInfo?
    if (connectivityManager != null) {
        networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected &&
                (networkInfo.type == ConnectivityManager.TYPE_WIFI
                        || networkInfo.type == ConnectivityManager.TYPE_MOBILE)
    }
    return false
}