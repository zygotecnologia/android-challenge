package com.zygotecnologia.zygotv.showdetails.view

data class SeasonViewModel(
    val title: String,
    val posterImageUrl: String,
    val episodes: List<EpisodeViewModel>
)