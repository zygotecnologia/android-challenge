package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class Episode(
    @Json(name = "episode_number")
    val episodeNumber: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "season_number")
    val seasonNumber: Int?
)