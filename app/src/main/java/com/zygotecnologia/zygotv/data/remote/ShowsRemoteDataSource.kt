package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.data.entity.*
import com.zygotecnologia.zygotv.domain.entity.*

interface ShowsRemoteDataSource {

    suspend fun fetchGenres(): GenresResponse?

    suspend fun fetchPopularShows(): ShowsResponse?

    suspend fun fetchShowsByGenresId(genresId: List<String>): ShowsResponse?

    suspend fun fetchShow(showId: Int): ShowResponse?

    suspend fun fetchSeason(showId: Int, seasonNumber: Int): SeasonResponse?

    suspend fun fetchShowSearch(searchQuery: String): SearchResponse?
}