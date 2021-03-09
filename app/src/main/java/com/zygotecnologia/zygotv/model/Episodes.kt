package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.modelGenre.Genre
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episodes(
    @Json(name = "name")
    val name: String?,
    @Json(name = "overview")
    val overview: String?,
    @Json(name = "episodes")
    val episodes: List<Episode>?,
): Parcelable
