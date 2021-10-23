package com.zygotecnologia.zygotv.tmdb.domain

data class Season(
    val id: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String,
    val posterPath: String?
)
