package com.zygotecnologia.zygotv.tmdb.domain

data class GenreWithShows(
    val genre: Genre,
    val shows: List<Show>
)