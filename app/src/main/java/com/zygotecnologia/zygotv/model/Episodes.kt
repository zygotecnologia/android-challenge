package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class EpisodesResponse(
    @Json(name = "episodes") val episodes: List<Episodes>?
)

data class Episodes(
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val description: String?
)