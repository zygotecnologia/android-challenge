package com.zygotecnologia.zygotv.tmdb.domain

data class Show(
    val id: Int,
    val genres: List<Genre>,
    val name: String,
    val backdropPath: String?,
    val posterPath: String?
)
