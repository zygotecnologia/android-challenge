package com.zygotecnologia.zygotv.data.entity

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.entity.Show

data class ShowResponse(
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

fun ShowResponse.toShowEntity() = Show(
    originalName = this.originalName,
    genreIds = this.genreIds,
    name = this.name,
    voteCount = this.voteCount,
    backdropPath = this.backdropPath,
    originalLanguage = this.originalLanguage,
    id = this.id,
    overview = this.overview,
    posterPath = this.posterPath,
    numberOfSeasons = this.numberOfSeasons,
    seasons = null
)