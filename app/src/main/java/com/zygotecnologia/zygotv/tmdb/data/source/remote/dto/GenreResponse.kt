package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)