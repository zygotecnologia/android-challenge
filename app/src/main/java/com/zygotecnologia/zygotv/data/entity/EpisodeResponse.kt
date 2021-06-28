package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.entity.Episode

data class EpisodeResponse(
    @Json(name = "episode_number")
    val episodeNumber: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "season_number")
    val seasonNumber: Int?
)

fun EpisodeResponse.toEpisodeEntity() = Episode(
    episodeNumber = this.episodeNumber,
    name = this.name,
    overview = this.overview,
    seasonNumber = this.seasonNumber
)