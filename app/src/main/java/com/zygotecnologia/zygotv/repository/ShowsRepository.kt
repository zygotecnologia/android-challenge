package com.zygotecnologia.zygotv.repository

import android.util.Log
import com.zygotecnologia.zygotv.model.*
import com.zygotecnologia.zygotv.network.TmdbApi
import retrofit2.HttpException
import retrofit2.await
import java.io.IOException
import java.net.SocketTimeoutException

class ShowsRepository(
    private val tmdbApi: TmdbApi
) {

    suspend fun fetchGenres(): GenreResponse? {
        return tmdbApi.fetchGenresAsync()
    }

    suspend fun fetchPopularShows(): ShowResponse? {
        return tmdbApi.fetchPopularShowsAsync()
    }

    suspend fun fetchShowsByGenresId(genresId: List<String>): ShowResponse? {
        return tmdbApi.fetchShowsByGenre(genresId.joinToString(","))
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