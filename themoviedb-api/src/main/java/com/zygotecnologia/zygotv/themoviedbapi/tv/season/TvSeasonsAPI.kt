package com.zygotecnologia.zygotv.themoviedbapi.tv.season

import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.season.model.SeasonModel
import retrofit2.http.GET
import retrofit2.http.Path

interface TvSeasonsAPI {
    @GET("${TheMovieDbAPI.API_VERSION}/tv/{tv_id}/season/{season_number}")
    suspend fun get(
        @Path("tv_id") id: Int,
        @Path("season_number") seasonNumber: Int
    ): SeasonModel
}