package com.zygotecnologia.zygotv.service.remote

import com.zygotecnologia.zygotv.service.remote.data.seasons.SeasonResponse
import com.zygotecnologia.zygotv.service.remote.data.seasonsdetail.SeasonDetail
import com.zygotecnologia.zygotv.service.remote.data.serie.GenreResponse
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponseList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(
        @Query("region") region: String
    ): Response<GenreResponse>

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("region") region: String
    ): Response<ShowResponseList>

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int,
        @Query("language") language: String
    ): Response<SeasonResponse>

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}")
    suspend fun fetchSeasonDetail(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("language") language: String
    ): Response<SeasonDetail>

    companion object {
        private const val TMDB_API_VERSION = "3"
    }
}