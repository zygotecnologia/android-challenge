package com.zygotecnologia.zygotv.repository

import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.network.TmdbApi

class ShowsRepository(
    private val tmdbApi: TmdbApi
) {

    suspend fun fetchGenres(): GenreResponse? {
        return tmdbApi.fetchGenresAsync()
    }

    suspend fun fetchPopularShows(): ShowResponse? {
        return tmdbApi.fetchPopularShowsAsync()
    }

    suspend fun fetchShow(showId: Int): Show? {
        return tmdbApi.fetchShowAsync(showId)
    }

    suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season? {
        return tmdbApi.fetchSeasonAsync(showId, seasonNumber)
    }

    suspend fun fetchShowSearch(searchQuery: String): Search? {
        return tmdbApi.fetchShowSearch(searchQuery)
    }
}