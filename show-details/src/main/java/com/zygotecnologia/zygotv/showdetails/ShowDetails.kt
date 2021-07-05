package com.zygotecnologia.zygotv.showdetails

data class ShowDetails(
    val id: Int,
    val name: String,
    val backdropImageUrl: String,
    val seasons: List<Season>
)