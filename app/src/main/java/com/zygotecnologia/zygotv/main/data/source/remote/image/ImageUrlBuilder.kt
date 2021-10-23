package com.zygotecnologia.zygotv.main.data.source.remote.image

import com.zygotecnologia.zygotv.tmdb.data.source.remote.service.TmdbService

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?api_key=" + TmdbService.TMDB_API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?api_key=" + TmdbService.TMDB_API_KEY
    }
}

fun String?.toTmdbPosterUrl() = this?.let { ImageUrlBuilder.buildPosterUrl(it) }

fun String?.toTmdbBackdropUrl() = this?.let { ImageUrlBuilder.buildBackdropUrl(it) }
