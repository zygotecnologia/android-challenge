package com.zygotecnologia.zygotv.model.show

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.zygotecnologia.zygotv.model.genre.Genre
import com.zygotecnologia.zygotv.model.season.Season
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowDetails(
    @SerializedName("genres")
    val genres: List<Genre>?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("seasons")
    val seasons: List<Season>?

) : Parcelable
