package com.zygotecnologia.zygotv.domain.entity

data class Season(
    val episodes: List<Episode>?,
    val overview: String?,
    val posterPath: String?,
    val seasonNumber: Int?
)