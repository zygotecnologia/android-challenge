package com.zygotecnologia.zygotv.data.model
import com.squareup.moshi.Json
import java.io.Serializable

data class SpokenLanguage(
    @Json(name ="english_name")
    val englishName: String,
    @Json(name ="iso_639_1")
    val iso6391: String,
    @Json(name ="name")
    val name: String
) : Serializable