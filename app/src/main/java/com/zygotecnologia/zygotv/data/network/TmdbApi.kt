package com.zygotecnologia.zygotv.data.network

import com.zygotecnologia.zygotv.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/tv/list")
    suspend fun fetchGenresAsync(): GenreResponse?

    @GET("tv/popular")
    suspend fun fetchPopularShowsAsync(): ShowResponse?

    @GET("discover/tv")
    suspend fun fetchShowsByGenre(
        @Query("with_genres") genres: String
    ): ShowResponse?

    @GET("tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): Show?

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonAsync(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber: Int
    ): Season?

    @GET("search/tv")
    suspend fun fetchShowSearch(
        @Query("query") query: String
    ): Search?

    companion object {
        const val TMDB_API_VERSION = "/3/"
        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
    }
}