package com.zygotecnologia.zygotv.tmdb.domain.show

import com.zygotecnologia.zygotv.tmdb.domain.season.SeasonWithEpisodes

data class ShowWithSeasons(
    val show: Show,
    val seasons: List<SeasonWithEpisodes>
)
