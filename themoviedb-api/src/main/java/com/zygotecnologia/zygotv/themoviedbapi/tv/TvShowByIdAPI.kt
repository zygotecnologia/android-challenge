package com.zygotecnologia.zygotv.themoviedbapi.tv

import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.model.ShowModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowByIdAPI {

    @GET("${TheMovieDbAPI.API_VERSION}/tv/{tv_id}")
    suspend fun get(
        @Path("tv_id") id: Int
    ): ShowModel
}