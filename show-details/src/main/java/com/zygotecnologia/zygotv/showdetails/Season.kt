package com.zygotecnologia.zygotv.showdetails

data class Season(
    val number: Int,
    val name: String,
    val posterImageUrl: String,
    val episodes: List<Episode>
)