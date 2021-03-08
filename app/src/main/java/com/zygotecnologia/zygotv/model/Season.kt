package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.modelGenre.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    @Json(name = "air_date")
    val air_date: String,
    @Json(name = "episode_count")
    val episode_count: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val poster_path: String,
    @Json(name = "season_number")
    val season_number: Int,
): Parcelable
