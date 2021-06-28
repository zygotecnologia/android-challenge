package com.zygotecnologia.zygotv.data.network

import com.zygotecnologia.zygotv.data.entity.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("genre/tv/list")
    suspend fun fetchGenresAsync(): GenresResponse?

    @GET("tv/popular")
    suspend fun fetchPopularShowsAsync(): ShowsResponse?

    @GET("discover/tv")
    suspend fun fetchShowsByGenre(
        @Query("with_genres") genres: String
    ): ShowsResponse?

    @GET("tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): ShowResponse?

    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonAsync(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber: Int
    ): SeasonResponse?

    @GET("search/tv")
    suspend fun fetchShowSearch(
        @Query("query") query: String
    ): SearchResponse?
}