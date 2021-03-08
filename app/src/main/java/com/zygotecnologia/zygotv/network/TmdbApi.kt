package com.zygotecnologia.zygotv.network

import com.zygotecnologia.zygotv.model.Episode
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowResponse
import com.zygotecnologia.zygotv.modelGenre.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.http.Query

interface TmdbApi {

    @GET("$TMDB_API_VERSION/genre/tv/list")
    fun fetchGenresAsync(
    ): Call<GenreResponse>

    @GET("$TMDB_API_VERSION/tv/popular")
    fun fetchPopularShowsAsync(
    ): Call<ShowResponse>

    @GET("$TMDB_API_VERSION/tv/{tv_id}")
    fun fetchShowAsync(
        @Path("tv_id") id: Int
    ): Call<Show>

    @GET("$TMDB_API_VERSION/tv/{tv_id}/season/{season_number}")
    fun fetchEpisodeAsync(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int
    ): Call<Episode>

    companion object {
        private const val TMDB_API_VERSION = "3"

        const val TMDB_BASE_URL = "https://api.themoviedb.org"
        const val TMDB_API_QUERY = "api_key"
        const val REGION = "BR"
    }
}