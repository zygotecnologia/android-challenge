package com.zygotecnologia.zygotv.model.season

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.zygotecnologia.zygotv.model.episode.Episode
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SeasonResponse(
    @SerializedName("season_number")
    val seasonNumber: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("episodes")
    val episodes: List<Episode>?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?
) : Parcelable
