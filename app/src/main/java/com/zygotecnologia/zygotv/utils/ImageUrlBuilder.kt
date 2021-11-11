package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.BuildConfig

object ImageUrlBuilder {

    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${BuildConfig.TMDB_API_QUERY}=" + BuildConfig.TMDB_API_KEY
    }
}