package com.zygotecnologia.zygotv.tmdb.domain.genre

import com.zygotecnologia.zygotv.tmdb.domain.show.Show

data class GenreWithShows(
    val genre: Genre,
    val shows: List<Show>
)