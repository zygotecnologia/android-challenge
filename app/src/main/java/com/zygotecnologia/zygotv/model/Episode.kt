package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Episode(
    @Json(name = "air_date")
    val id: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "episode_number")
    val episodeNumber: Int?,

    @Json(name = "overview")
    val overview : String?
):Serializable
