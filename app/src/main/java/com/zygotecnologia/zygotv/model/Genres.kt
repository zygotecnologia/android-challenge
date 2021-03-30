package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>
)

data class Genre(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)