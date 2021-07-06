package com.zygotecnologia.zygotv.themoviedbapi.tv.model

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.themoviedbapi.genre.model.GenreModel
import com.zygotecnologia.zygotv.themoviedbapi.tv.season.model.SeasonModel

data class ShowModel(
    val genres: List<GenreModel>?,
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
    val posterPath: String?,
    @Json(name = "seasons")
    val seasons: List<SeasonModel>?
)