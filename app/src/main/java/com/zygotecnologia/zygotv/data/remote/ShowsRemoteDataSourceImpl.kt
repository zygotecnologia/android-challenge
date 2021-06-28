package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.data.entity.*
import com.zygotecnologia.zygotv.data.network.TmdbApi

class ShowsRemoteDataSourceImpl(
    private val tmdbApi: TmdbApi
): ShowsRemoteDataSource {

    override suspend fun fetchGenres(): GenresResponse? {
        return tmdbApi.fetchGenresAsync()
    }

    override suspend fun fetchPopularShows(): ShowsResponse? {
        return tmdbApi.fetchPopularShowsAsync()
    }

    override suspend fun fetchShowsByGenresId(genresId: List<String>): ShowsResponse? {
        return tmdbApi.fetchShowsByGenre(genresId.joinToString(","))
    }

    override suspend fun fetchShow(showId: Int): ShowResponse? {
        return tmdbApi.fetchShowAsync(showId)
    }

    override suspend fun fetchSeason(showId: Int, seasonNumber: Int): SeasonResponse? {
        return tmdbApi.fetchSeasonAsync(showId, seasonNumber)
    }

    override suspend fun fetchShowSearch(searchQuery: String): SearchResponse? {
        return tmdbApi.fetchShowSearch(searchQuery)
    }
}