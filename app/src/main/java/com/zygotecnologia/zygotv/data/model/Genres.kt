package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.Genre

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre> = emptyList()
)

data class Genre(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)