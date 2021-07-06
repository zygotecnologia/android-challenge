package com.zygotecnologia.zygotv.themoviedbapi.tv.season.model

import com.squareup.moshi.Json

data class SeasonModel(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "season_number")
    val seasonNumber: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overView: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "episodes")
    val episodes: List<EpisodeModel>?
)