package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.modelGenre.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
    val genres: List<Genre>?,
    @Json(name = "original_name")
    val originalName: String?,
    @Json(name = "genre_ids")
    val genreIds: List<Int>?,
    @Json(name = "seasons")
    val seasons: List<Season>?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "vote_count")
    val voteCount: Int?,
    @Json(name = "backdrop_path")
    val backdropPath: String?,
    @Json(name = "original_language")
    val originalLanguage: String?,
    @Json(name = "id")
    @PrimaryKey
    val id: Int?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "popularity")
    val popularity: String?,
    @Transient
    var expanded: Boolean = false
): Parcelable
