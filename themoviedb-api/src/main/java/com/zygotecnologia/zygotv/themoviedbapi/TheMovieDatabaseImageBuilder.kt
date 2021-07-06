package com.zygotecnologia.zygotv.themoviedbapi

interface TheMovieDatabaseImageBuilder {
    fun getPosterCompleteUrl(path: String): String
    fun getBackdropCompleteUrl(path: String): String

    companion object Factory {
        fun make(): TheMovieDatabaseImageBuilder = TheMovieDatabaseImageBuilderImpl(TheMovieDbAPI.API_KEY)
    }
}