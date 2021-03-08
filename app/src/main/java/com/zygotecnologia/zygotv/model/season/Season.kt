package com.zygotecnologia.zygotv.model.season

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Season(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("season_number")
    val seasonNumber: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?
) : Parcelable
