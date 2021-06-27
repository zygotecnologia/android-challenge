package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.model.*

interface ShowsRemoteDataSource {

    suspend fun fetchGenres(): GenreResponse?

    suspend fun fetchPopularShows(): ShowResponse?

    suspend fun fetchShowsByGenresId(genresId: List<String>): ShowResponse?

    suspend fun fetchShow(showId: Int): Show?

    suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season?

    suspend fun fetchShowSearch(searchQuery: String): Search?
}