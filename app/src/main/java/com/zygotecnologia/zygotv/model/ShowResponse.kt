package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class ShowResponse(
    @Json(name = "page")
    val page: Int?,

    @Json(name = "total_results")
    val totalResults: Int?,

    @Json(name = "total_pages")
    val totalPages: Int?,

    @Json(name = "results")
    val results: List<ShowDetails>?
)
