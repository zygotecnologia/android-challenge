package com.zygotecnologia.zygotv.tmdb.presentation.seasons

import com.zygotecnologia.zygotv.tmdb.domain.episode.Episode
import com.zygotecnologia.zygotv.tmdb.domain.season.Season

sealed class ShowDetailItem {

    data class SeasonItem(
        val season: Season,
        val isCollapsed: Boolean
    ): ShowDetailItem()

    data class EpisodeItem(
        val episode: Episode
    ): ShowDetailItem()
}
