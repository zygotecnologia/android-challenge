package com.zygotecnologia.zygotv.data.api

import com.zygotecnologia.zygotv.data.model.genre.GenreResponse
import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.data.model.show.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val TMDB_API_VERSION = "3"
interface ApiService {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): GenreResponse?

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): ShowResponse?

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ): Show?
}