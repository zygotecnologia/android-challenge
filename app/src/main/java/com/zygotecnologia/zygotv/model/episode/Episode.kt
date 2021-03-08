package com.zygotecnologia.zygotv.model.episode

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Episode(
    @SerializedName("episode_number")
    val episodeNumber: Int?,

    @SerializedName("season_number")
    val seasonNumber: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("overview")
    val overview: String?
) : Parcelable
