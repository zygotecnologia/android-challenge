package com.zygotecnologia.zygotv.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ShowResponse(
    @Json(name = "page")
    val page: Int?,

    @Json(name = "total_results")
    val totalResults: Int?,

    @Json(name = "total_pages")
    val totalPages: Int?,

    @Json(name = "results")
    val results: List<Show>?

) : Parcelable
