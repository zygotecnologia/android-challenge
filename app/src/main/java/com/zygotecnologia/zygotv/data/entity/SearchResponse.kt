package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "results")
    val results: List<ShowResponse>?
)
