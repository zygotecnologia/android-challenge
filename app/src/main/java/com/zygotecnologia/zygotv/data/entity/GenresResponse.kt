package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json


data class GenresResponse(
    @Json(name = "genres")
    val genres: List<GenreResponse>?
)