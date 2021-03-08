package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreCategory(
    var id: Int,
    var genreName: String,
    var shows: ArrayList<Show>? = null
): Parcelable