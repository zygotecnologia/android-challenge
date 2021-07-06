package com.zygotecnologia.zygotv.themoviedbapi.genre.tv

import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import com.zygotecnologia.zygotv.themoviedbapi.genre.model.GenresResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface GenresListAPI {

    @GET("${TheMovieDbAPI.API_VERSION}/genre/tv/list")
    suspend fun get(): GenresResponseModel
}