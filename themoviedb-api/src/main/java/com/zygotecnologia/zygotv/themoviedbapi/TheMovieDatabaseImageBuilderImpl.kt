package com.zygotecnologia.zygotv.themoviedbapi

internal class TheMovieDatabaseImageBuilderImpl(private val apiKey: String): TheMovieDatabaseImageBuilder {

    override fun getPosterCompleteUrl(path: String) = "$POSTER_URL$path?api_key=$apiKey"

    override fun getBackdropCompleteUrl(path: String) = "$BACKDROP_URL$path?api_key=$apiKey"

    private val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

}