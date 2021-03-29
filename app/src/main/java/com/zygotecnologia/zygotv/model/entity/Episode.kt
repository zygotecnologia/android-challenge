package com.zygotecnologia.zygotv.model.entity

import com.squareup.moshi.Json

data class Episode(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?
)
