package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class Season(
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val poster_path: String?
)