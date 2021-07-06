package com.zygotecnologia.zygotv.themoviedbapi

interface TheMovieDatabaseImageUrlBuilder {
    fun getPosterCompleteUrl(path: String): String
    fun getBackdropCompleteUrl(path: String): String

    companion object Factory {
        fun make(): TheMovieDatabaseImageUrlBuilder = TheMovieDatabaseImageUrlBuilderImpl(TheMovieDbAPI.API_KEY)
    }
}