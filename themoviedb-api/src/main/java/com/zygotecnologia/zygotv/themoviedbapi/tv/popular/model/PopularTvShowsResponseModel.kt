package com.zygotecnologia.zygotv.themoviedbapi.tv.popular.model

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.themoviedbapi.tv.model.ShowModel

data class PopularTvShowsResponseModel(
    @Json(name = "page")
    val page: Int?,

    @Json(name = "total_results")
    val totalResults: Int?,

    @Json(name = "total_pages")
    val totalPages: Int?,

    @Json(name = "results")
    val results: List<ShowModel>?
)