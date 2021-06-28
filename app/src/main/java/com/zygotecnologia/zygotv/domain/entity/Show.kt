package com.zygotecnologia.zygotv.domain.entity

data class Show(
    val originalName: String?,
    val genreIds: List<Int>?,
    val name: String?,
    val voteCount: Int?,
    val backdropPath: String?,
    val originalLanguage: String?,
    val id: Int?,
    val overview: String?,
    val posterPath: String?,
    val numberOfSeasons: Int?,
    var seasons: List<Season>?
)
