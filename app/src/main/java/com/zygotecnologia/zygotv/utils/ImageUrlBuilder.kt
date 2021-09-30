package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.BuildConfig

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    const val TMDB_API_QUERY = "api_key"
    private const val TMDB_API_KEY = BuildConfig.apiKey

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?${TMDB_API_QUERY}=" + TMDB_API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${TMDB_API_QUERY}=" + TMDB_API_KEY
    }
}