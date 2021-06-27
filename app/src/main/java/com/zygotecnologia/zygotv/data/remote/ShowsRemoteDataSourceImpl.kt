package com.zygotecnologia.zygotv.data.remote

import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.model.*

class ShowsRemoteDataSourceImpl(
    private val tmdbApi: TmdbApi
): ShowsRemoteDataSource {
    override suspend fun fetchGenres(): GenreResponse? {
        return tmdbApi.fetchGenresAsync()
    }

    override suspend fun fetchPopularShows(): ShowResponse? {
        return tmdbApi.fetchPopularShowsAsync()
    }

    override suspend fun fetchShowsByGenresId(genresId: List<String>): ShowResponse? {
        return tmdbApi.fetchShowsByGenre(genresId.joinToString(","))
    }

    override suspend fun fetchShow(showId: Int): Show? {
        return tmdbApi.fetchShowAsync(showId)
    }

    override suspend fun fetchSeason(showId: Int, seasonNumber: Int): Season? {
        return tmdbApi.fetchSeasonAsync(showId, seasonNumber)
    }

    override suspend fun fetchShowSearch(searchQuery: String): Search? {
        return tmdbApi.fetchShowSearch(searchQuery)
    }
}