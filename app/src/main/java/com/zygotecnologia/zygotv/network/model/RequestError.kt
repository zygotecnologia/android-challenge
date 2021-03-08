package com.zygotecnologia.zygotv.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
open class RequestError(

    @field:Json(name = "code")
    val code: Int = -1,

    @field:Json(name = "title")
    val title: String = "",

    @field:Json(name = "message")
    val message: String = ""

) : Parcelable