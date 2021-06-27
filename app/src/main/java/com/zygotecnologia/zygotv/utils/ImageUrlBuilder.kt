package com.zygotecnologia.zygotv.utils

import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.utils.secret.Keys
import java.util.*

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String) = POSTER_URL + posterPath + getImageUrlQueryString()

    fun buildBackdropUrl(backdropPath: String) = BACKDROP_URL + backdropPath + getImageUrlQueryString()

    private fun getImageUrlQueryString() =
                "?${TmdbApi.TMDB_API_QUERY}=" + Keys.apiKey() +
                "&language=" + Locale.getDefault().toLanguageTag()
}