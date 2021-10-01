package com.zygotecnologia.zygotv.data.model.show

import com.google.gson.annotations.SerializedName

data class ShowResponse(
    @SerializedName("page")
    val page: Int?,

    @SerializedName("total_results")
    val totalResults: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("results")
    val results: List<Show>?
)
