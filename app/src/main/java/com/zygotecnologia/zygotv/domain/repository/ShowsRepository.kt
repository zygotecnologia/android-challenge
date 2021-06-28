package com.zygotecnologia.zygotv.domain.repository

import com.zygotecnologia.zygotv.domain.entity.Genre
import com.zygotecnologia.zygotv.domain.entity.Season
import com.zygotecnologia.zygotv.domain.entity.Show

interface ShowsRepository {

    suspend fun fetchGenres(): List<Genre>?

    suspend fun fetchPopularShows(): List<Show>?

    suspend fun fetchShowsByGenresId(genresId: List<String>): List<Show>?

    suspend fun fetchShow(showId: Int): Show?

    suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season?

    suspend fun fetchShowSearch(searchQuery: String): List<Show>?
}