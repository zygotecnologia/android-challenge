package com.zygotecnologia.zygotv.data.model.ShowDetailsModel

data class NextEpisodeToAir(
    val air_date: String,
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val production_code: String,
    val season_number: Int,
    val still_path: Any,
    val vote_average: Double,
    val vote_count: Int
)