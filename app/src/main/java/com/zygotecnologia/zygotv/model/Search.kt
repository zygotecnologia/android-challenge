package com.zygotecnologia.zygotv.model

import com.squareup.moshi.Json

data class Search(
    @Json(name = "results")
    val results: List<Show>?
)
