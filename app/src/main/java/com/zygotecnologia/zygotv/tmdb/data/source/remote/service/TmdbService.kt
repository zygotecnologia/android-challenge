package com.zygotecnologia.zygotv.tmdb.data.source.remote.service

import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.GenreListResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowsPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbService {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    suspend fun fetchGenresAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): GenreListResponse

    @GET("$TMDB_API_VERSION/tv/popular")
    suspend fun fetchPopularShowsAsync(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): ShowsPageResponse

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    suspend fun fetchShowAsync(
        @Query("api_key") apiKey: String,
        @Path("tv_id") id: Int
    ): ShowResponse

    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
        const val TMDB_API_KEY = "27490b1bf49c0e5ffaa07dfd947e9605"
        const val TMDB_BASE_IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }
}