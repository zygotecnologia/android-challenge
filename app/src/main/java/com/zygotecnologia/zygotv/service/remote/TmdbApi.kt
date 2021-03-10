package com.zygotecnologia.zygotv.service.remote

import com.zygotecnologia.zygotv.service.remote.data.GenreResponse
import com.zygotecnologia.zygotv.service.remote.data.ShowResponse
import com.zygotecnologia.zygotv.service.remote.data.ShowResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
     suspend fun fetchGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Response<GenreResponse>

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): Response<ShowResponseList>

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Query("api_key") apiKey: String,
        @Path("tv_id") id: Int
    ): ShowResponse?

    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
    }
}