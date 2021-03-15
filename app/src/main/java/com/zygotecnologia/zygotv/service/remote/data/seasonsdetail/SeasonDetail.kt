package com.zygotecnologia.zygotv.service.remote.data.seasonsdetail


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonDetail(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episodes")
    val episodes: List<Episode>,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int?
)