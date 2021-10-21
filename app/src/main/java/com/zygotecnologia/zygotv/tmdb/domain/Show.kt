package com.zygotecnologia.zygotv.tmdb.domain

data class Show(
    val id: Int,
    val originalName: String,
    val genres: List<Genre>,
    val name: String,
    val voteCount: Int,
    val originalLanguage: String,
    val overview: String,
    val backdropPath: String?,
    val posterPath: String?
)
