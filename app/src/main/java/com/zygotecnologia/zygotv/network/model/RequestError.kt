package com.zygotecnologia.zygotv.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
open class RequestError(

    @field:Json(name = "code")
    val code: Int = -1,

    val title: String = "ops!",

    @field:Json(name = "status_message")
    val message: String = ""

) : Parcelable