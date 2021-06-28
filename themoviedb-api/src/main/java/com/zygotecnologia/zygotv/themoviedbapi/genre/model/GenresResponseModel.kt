package com.zygotecnologia.zygotv.themoviedbapi.genre.model

import com.squareup.moshi.Json

data class GenresResponseModel(
    @Json(name = "genres") val genres: List<GenreModel>?
)