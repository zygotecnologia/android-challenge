package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Show(
    @SerializedName("genres")
    val genres: List<Genre>?,

    @SerializedName("original_name")
    val originalName: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("poster_path")
    val posterPath: String?
) : Parcelable
