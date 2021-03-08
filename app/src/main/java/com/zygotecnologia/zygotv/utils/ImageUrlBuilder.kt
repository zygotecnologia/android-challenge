package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.network.TmdbApi

object ImageUrlBuilder {

    const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?${TmdbApi.TMDB_API_QUERY}=" + BuildConfig.ApiKey
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${TmdbApi.TMDB_API_QUERY}=" + BuildConfig.ApiKey
    }
}