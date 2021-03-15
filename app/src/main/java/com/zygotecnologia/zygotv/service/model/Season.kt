package com.zygotecnologia.zygotv.service.model

import com.zygotecnologia.zygotv.service.remote.data.seasons.LastEpisodeToAir
import com.zygotecnologia.zygotv.service.remote.data.seasons.NextEpisodeToAir
import com.zygotecnologia.zygotv.service.remote.data.seasons.Season
import com.zygotecnologia.zygotv.service.remote.data.seasons.SeasonResponse

data class Season(
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val lastAirDate: String,
    val lastEpisodeToAir: LastEpisodeToAir?,
    val name: String,
    val nextEpisodeToAir: NextEpisodeToAir?,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val seasons: List<Season>,
)

