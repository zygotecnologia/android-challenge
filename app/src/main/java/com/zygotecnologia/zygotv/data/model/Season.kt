package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json

data class Season(
    @Json(name = "episodes")
    val episodes: List<Episode>?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int?
){
    val getSeasonTitle : String
        get() = "Season ${this.seasonNumber}"
}