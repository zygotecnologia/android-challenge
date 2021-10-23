package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class ShowDetailsResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "poster_path") val posterPath: String?
)
