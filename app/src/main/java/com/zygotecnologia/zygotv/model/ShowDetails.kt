package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class ShowDetails(
    @Json(name = "backdrop_path")
    val backdrop_path: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "original_name")
    val original_name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val poster_path: String?,
    @Json(name = "seasons")
    val seasons: List<Season>?
)