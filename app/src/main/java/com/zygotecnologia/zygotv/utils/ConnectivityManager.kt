package com.zygotecnologia.zygotv.utils

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

class ConnectivityManager(
    application: Application,
) {
    private val connectionLiveData = InternetLiveData(application)

    val isNetworkAvailable = MutableLiveData(false)

    fun registerConnectionObserver(lifecycleOwner: LifecycleOwner) {
        connectionLiveData.observe(lifecycleOwner, { isConnected ->
            isConnected?.let { isNetworkAvailable.value = it }
        })
    }

    fun unregisterConnectionObserver(lifecycleOwner: LifecycleOwner) {
        connectionLiveData.removeObservers(lifecycleOwner)
    }
}