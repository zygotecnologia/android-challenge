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
//        try {
            return tmdbApi.fetchGenresAsync()
//        } catch (e: Exception) {
//            when(e) {
//                is HttpException -> {
//                    Log.e("zygo", "http exception")
//                }
//                is SocketTimeoutException -> {
//                    Log.e("zygo", "socket timeout exception")
//                }
//                is IOException -> {
//                    Log.e("zygo", "io exception")
//                }
//                else -> Log.e("zygo", "unknown exception")
//            }
//            throw e
//        }
    }

    suspend fun fetchPopularShows(): ShowResponse? {
//        try {
            return tmdbApi.fetchPopularShowsAsync()
//        } catch (e: Exception) {
//            Log.e("zygo", "exception", e)
//            throw e
//        }
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