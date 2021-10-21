package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class ShowsPageResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "total_results") val totalResults: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "results") val results: List<ShowResponse>
)
