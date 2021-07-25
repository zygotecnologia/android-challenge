package com.zygotecnologia.zygotv.domain.model

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.model.Genre

data class Show(
    val genres: List<Genre>?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "vote_count")
    val voteCount: Int?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?
)
