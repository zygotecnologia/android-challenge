package com.zygotecnologia.zygotv.vo

import com.squareup.moshi.Json
import java.io.Serializable

data class CreatedBy(
   @Json(name ="credit_id")
    val creditId: String,
   @Json(name ="gender")
    val gender: Int,
   @Json(name ="id")
    val id: Int,
   @Json(name ="name")
    val name: String,
) : Serializable