package com.zygotecnologia.zygotv.themoviedbapi.genre.model

import com.squareup.moshi.Json

class GenreModel(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)