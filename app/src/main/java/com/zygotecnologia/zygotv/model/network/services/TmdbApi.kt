package com.zygotecnologia.zygotv.model.network.services

import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.model.network.response.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(): GenreResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(): ShowResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): Show?

    companion object {
        private const val TMDB_API_VERSION = "3"
    }
}