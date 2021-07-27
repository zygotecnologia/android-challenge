package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json
import com.zygotecnologia.zygotv.domain.model.Genre

data class GenreResponse(
    @Json(name = "genres") val genres: List<Genre>?
)