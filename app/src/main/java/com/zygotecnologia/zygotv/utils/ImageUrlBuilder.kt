package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.network.TmdbService

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?${TmdbService.TMDB_API_QUERY}=" + TmdbService.TMDB_API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${TmdbService.TMDB_API_QUERY}=" + TmdbService.TMDB_API_KEY
    }
}