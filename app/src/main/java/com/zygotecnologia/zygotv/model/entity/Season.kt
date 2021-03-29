package com.zygotecnologia.zygotv.model.entity

import com.squareup.moshi.Json

data class Season(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "poster_path")
    val poster: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "season_number")
    val seasonNumber: Int?,
    @Json(name = "episodes")
    val episodes: List<Episode>?,
    var showEpisodes: Boolean = false
)
