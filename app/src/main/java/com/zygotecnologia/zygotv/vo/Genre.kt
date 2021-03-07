package com.zygotecnologia.zygotv.vo
import com.squareup.moshi.Json
import java.io.Serializable

data class Genre(
    @Json(name ="id")
    val id: Int,
    @Json(name ="name")
    val name: String
) : Serializable