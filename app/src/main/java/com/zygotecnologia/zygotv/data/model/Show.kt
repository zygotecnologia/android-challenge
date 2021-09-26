package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json

data class Show(
    @Json(name ="original_name")
    val originalName: String?,
    @Json(name ="genre_ids")
    val genreIds: List<Int>?,
    @Json(name ="name")
    val name: String?,
    @Json(name ="vote_count")
    val voteCount: Int?,
    @Json(name ="backdrop_path")
    val backdropPath: String?,
    @Json(name ="original_language")
    val originalLanguage: String?,
    @Json(name ="id")
    val id: Int?,
    @Json(name ="overview")
    val overview: String?,
    @Json(name ="poster_path")
    val posterPath: String?,
    @Json(name ="number_of_seasons")
    val numberOfSeasons: Int?
)
