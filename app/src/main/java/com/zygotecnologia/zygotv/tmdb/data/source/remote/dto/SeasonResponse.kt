package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class SeasonResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "season_number") val seasonNumber: Int,
    @Json(name = "name") val name: String,
    @Json(name = "overview") private val overview: String,
    @Json(name = "poster_path") val posterPath: String
)
