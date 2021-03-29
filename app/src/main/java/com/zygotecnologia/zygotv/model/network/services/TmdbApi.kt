package com.zygotecnologia.zygotv.model.network.services

import com.zygotecnologia.zygotv.model.GenreResponse
import com.zygotecnologia.zygotv.model.entity.Season
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.model.network.response.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(): GenreResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(): ShowResponse?

    @GET("$TMDB_API_VERSION/search/tv")
    suspend fun fetchSearchShowAsync(
        @Query("query") query: String
    ): ShowResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): Show?

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonAsync(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber: Int
    ): Season?

    companion object {
        private const val TMDB_API_VERSION = "3"
    }
}