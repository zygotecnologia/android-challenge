package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.entity.Genre

data class GenreResponse(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)

fun GenreResponse.toGenreEntity() = Genre(
    id = this.id,
    name = this.name,
    shows = null
)