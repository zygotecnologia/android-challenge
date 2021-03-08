package com.zygotecnologia.zygotv.network.api.service

import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.model.genre.GenreResponse
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.model.show.ShowDetails
import com.zygotecnologia.zygotv.model.show.ShowResponse
import com.zygotecnologia.zygotv.utils.ApiKeyLibrary
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("genre/tv/list")
    suspend fun fetchGenres(@Query("api_key") apiKey: String = Keys.tmdbApiKey,
                            @Query("region") region: String = BuildConfig.TMDB_API_REGION): Response<GenreResponse>

    @GET("tv/popular")
    suspend fun fetchPopularShows(@Query("api_key") apiKey: String = Keys.tmdbApiKey,
                                  @Query("region") region: String = BuildConfig.TMDB_API_REGION): Response<ShowResponse>

    @GET("tv/{id}")
    suspend fun fetchShow(@Path("id") id: Int,
                          @Query("api_key") apiKey: String = Keys.tmdbApiKey,
                          @Query("region") region: String = BuildConfig.TMDB_API_REGION): Response<ShowDetails>

    @GET("tv/{id}/season/{season_number}")
    suspend fun fetchSeasonDetails(@Path("id") id: Int, @Path("season_number") seasonNumber: Int,
                                   @Query("api_key") apiKey: String = Keys.tmdbApiKey,
                                   @Query("region") region: String = BuildConfig.TMDB_API_REGION): Response<SeasonResponse>

    companion object Keys  {
        val tmdbApiKey = ApiKeyLibrary.getApiKey()
    }

}