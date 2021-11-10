package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>?
)
@Parcelize
data class Genre(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
):Parcelable