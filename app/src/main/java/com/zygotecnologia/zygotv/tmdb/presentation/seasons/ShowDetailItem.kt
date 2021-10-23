package com.zygotecnologia.zygotv.tmdb.presentation.seasons

import com.zygotecnologia.zygotv.tmdb.domain.Episode
import com.zygotecnologia.zygotv.tmdb.domain.Season

sealed class ShowDetailItem {

    data class SeasonItem(
        val season: Season
    ): ShowDetailItem()

    data class EpisodeItem(
        val episode: Episode
    ): ShowDetailItem()
}
