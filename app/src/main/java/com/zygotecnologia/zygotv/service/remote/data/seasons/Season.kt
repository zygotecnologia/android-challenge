package com.zygotecnologia.zygotv.service.remote.data.seasons


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.zygotecnologia.zygotv.service.remote.data.seasonsdetail.SeasonDetail

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "air_date")
    val airDate: String,
    @Json(name = "episode_count")
    val episodeCount: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String? = null,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int,
    var inVisibleDetails:Boolean = false,
    @Transient
    val seasonDetails : SeasonDetail? = null

)