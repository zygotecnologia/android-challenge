package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class GenreListResponse(
    @Json(name = "genres") val genreResponses: List<GenreResponse>
)
