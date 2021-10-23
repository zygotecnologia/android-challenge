package com.zygotecnologia.zygotv.tmdb.domain.season

import com.zygotecnologia.zygotv.tmdb.domain.episode.Episode

data class SeasonWithEpisodes(
    val season: Season,
    val episodes: List<Episode>
)
