package com.zygotecnologia.zygotv.data.repository

import com.zygotecnologia.zygotv.data.api.TmdbApi
import com.zygotecnologia.zygotv.data.api.TmdbApi.Companion.TMDB_API_KEY

class ShowsRepository (
    private val api: TmdbApi
): BaseRepository() {

    suspend fun getShows() = safeApiCall {
        api.fetchPopularShowsAsync(TMDB_API_KEY, "BR")
    }

    suspend fun getGenres() = safeApiCall {
        api.fetchGenresAsync(TMDB_API_KEY, "BR")
    }

    suspend fun getShowDetails(ShowId : Int) = safeApiCall {
        api.fetchShowAsync(TMDB_API_KEY, ShowId )
    }
}