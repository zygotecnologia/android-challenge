package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.BuildConfig

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?${BuildConfig.TMDB_API_KEY_QUERY}=" + BuildConfig.TMDB_API_KEY_VALUE
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${BuildConfig.TMDB_API_KEY_QUERY}=" + BuildConfig.TMDB_API_KEY_VALUE
    }
}