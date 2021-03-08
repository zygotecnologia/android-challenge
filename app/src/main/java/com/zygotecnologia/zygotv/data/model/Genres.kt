package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>?
)

data class Genres(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)