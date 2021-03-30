package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class SeasonDetail(
    @Json(name = "_id")
    val id: Int?,

    @Json(name = "episodes")
    val episodes: List<Episode>
)