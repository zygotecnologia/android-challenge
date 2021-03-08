package com.zygotecnologia.zygotv.network.retrofit

import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.network.model.RequestError
import com.zygotecnologia.zygotv.network.model.Resource

fun <T> Resource<T>.validateResponse(success: MutableLiveData<T>, error: MutableLiveData<RequestError>) {
    when (this.status) {
        Resource.Status.SUCCESS -> {
            this.data?.let {
                success.value = it
            }
        }
        else -> this.error?.let {
            error.value = it
        }
    }
}
