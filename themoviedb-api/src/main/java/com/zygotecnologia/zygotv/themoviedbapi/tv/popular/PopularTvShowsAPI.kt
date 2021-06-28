package com.zygotecnologia.zygotv.themoviedbapi.tv.popular

import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import com.zygotecnologia.zygotv.themoviedbapi.tv.popular.model.PopularTvShowsResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PopularTvShowsAPI {

    @GET("${TheMovieDbAPI.API_VERSION}/tv/popular")
    suspend fun get(): PopularTvShowsResponseModel
}