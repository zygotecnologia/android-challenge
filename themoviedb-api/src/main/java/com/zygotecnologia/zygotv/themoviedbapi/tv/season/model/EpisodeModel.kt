package com.zygotecnologia.zygotv.themoviedbapi.tv.season.model

import com.squareup.moshi.Json

data class EpisodeModel(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?
)