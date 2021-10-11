package com.zygotecnologia.zygotv.domain.model

import com.squareup.moshi.Json

data class Season(

    val genres: List<Genre>?,

    @Json(name = "season_number")
    val original_name: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "poster_path")
    val posterPath: String?
)
