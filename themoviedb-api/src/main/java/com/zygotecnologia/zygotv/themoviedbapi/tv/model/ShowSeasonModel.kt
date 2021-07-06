package com.zygotecnologia.zygotv.themoviedbapi.tv.model

import com.squareup.moshi.Json

data class ShowSeasonModel(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "season_number")
    val seasonNumber: Int?
)