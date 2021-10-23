package com.zygotecnologia.zygotv.tmdb.domain.season

data class Season(
    val id: Int,
    val seasonNumber: Int,
    val name: String,
    val overview: String,
    val posterPath: String?
)
