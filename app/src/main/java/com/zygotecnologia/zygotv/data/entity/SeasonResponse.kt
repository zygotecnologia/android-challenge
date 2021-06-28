package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.entity.Season

data class SeasonResponse(
    @Json(name = "episodes")
    val episodes: List<EpisodeResponse>?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "seasonNumber")
    val seasonNumber: Int?
)

fun SeasonResponse.toSeasonEntity() = Season(
    episodes = this.episodes?.map { it.toEpisodeEntity() },
    overview = this.overview,
    posterPath = this.posterPath,
    seasonNumber = this.seasonNumber
)