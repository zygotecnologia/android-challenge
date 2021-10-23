package com.zygotecnologia.zygotv.tmdb.domain

data class ShowWithSeasons(
    val show: Show,
    val seasons: List<SeasonWithEpisodes>
)
