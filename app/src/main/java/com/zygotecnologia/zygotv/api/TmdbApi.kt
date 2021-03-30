package com.zygotecnologia.zygotv.api

import com.zygotecnologia.zygotv.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): GenreResponse

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): ShowResponse

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): ShowDetails

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonAsync(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber:Int,
        @Query("api_key") apiKey: String
    ):Season

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}/episode/{episode_number}")
    suspend fun fetchEpisodeAsync(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber:Int,
        @Path("episode_number") episodeNumber:Int,
        @Query("api_key") apiKey: String
    ): Episode


    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
        const val TMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }
}