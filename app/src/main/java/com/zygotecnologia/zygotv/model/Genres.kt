package com.zygotecnologia.zygotv.modelGenre

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>?
):Parcelable

@Parcelize
@Entity
data class Genre(
    @Json(name = "id")
    @PrimaryKey
    val id: Int?,
    @Json(name = "name")
    val name: String?
):Parcelable