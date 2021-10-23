package com.zygotecnologia.zygotv.tmdb.data.source.remote.dto

import com.squareup.moshi.Json

data class SeasonDetailsResponse(
    @Json(name = "episodes") val episodes: List<EpisodeResponse>
)
