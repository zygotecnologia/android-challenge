package com.zygotecnologia.zygotv.model

import java.lang.Exception

sealed class ApiResponse<out T : Any> {
    data class Sucess<out T : Any>(val data:T): ApiResponse<T>()
    data class Failure(val exception: Exception): ApiResponse<Nothing>()
}