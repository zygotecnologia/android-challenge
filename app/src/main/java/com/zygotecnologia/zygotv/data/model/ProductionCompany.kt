package com.zygotecnologia.zygotv.data.model

import com.squareup.moshi.Json
import java.io.Serializable


data class ProductionCompany(
    @Json(name ="id")
    val id: Int,
    @Json(name ="logo_path")
    val logoPath: String?,
    @Json(name ="name")
    val name: String,
    @Json(name ="origin_country")
    val originCountry: String
) : Serializable