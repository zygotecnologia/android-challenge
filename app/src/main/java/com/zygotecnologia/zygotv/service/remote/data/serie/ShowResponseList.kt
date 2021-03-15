package com.zygotecnologia.zygotv.service.remote.data.serie

import com.squareup.moshi.Json

data class ShowResponseList(
    @Json(name = "page")
    val page: Int?,

    @Json(name = "total_results")
    val totalResults: Int?,

    @Json(name = "total_pages")
    val totalPages: Int?,

    @Json(name = "results")
    val results: List<ShowResponse>?
)
