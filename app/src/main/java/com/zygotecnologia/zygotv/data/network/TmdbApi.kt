package com.zygotecnologia.zygotv.data.network

import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("${BuildConfig.TMDB_API_VERSION}/genre/tv/list")
    suspend fun fetchGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): GenreResponse?

    @GET("${BuildConfig.TMDB_API_VERSION}/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): ShowResponse?

    @GET("${BuildConfig.TMDB_API_VERSION}/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): ShowDetails?
}