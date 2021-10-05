package com.zygotecnologia.zygotv.data.network

import com.zygotecnologia.zygotv.domain.model.GenreResponse
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.domain.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

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
        @Query("api_key") apiKey: String,
        @Path("tv_id") id: Int
    ): Show?

    @GET("$TMDB_API_VERSION/movie/top_rated")
    suspend fun fetchMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): ShowResponse?

    @GET("$TMDB_API_VERSION/genre/movie/list")
    suspend fun fetchGenresMovieAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): GenreResponse?

    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
        const val TMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }
}