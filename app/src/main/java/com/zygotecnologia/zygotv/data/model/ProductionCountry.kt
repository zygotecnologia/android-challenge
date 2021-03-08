package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json
import java.io.Serializable

data class ProductionCountry(
   @Json(name ="iso_3166_1")
    val iso31661: String,
   @Json(name ="name")
    val name: String
) : Serializable