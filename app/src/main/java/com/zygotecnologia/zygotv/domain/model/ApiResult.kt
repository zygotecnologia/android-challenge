package com.zygotecnologia.zygotv.domain.model

sealed class ApiResult {
    class Success(val series: List<Show>?) : ApiResult()
    class ServerError (val message: String? = null) : ApiResult()
}