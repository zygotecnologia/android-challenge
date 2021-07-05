package com.zygotecnologia.zygotv.showdetails.repository

import com.zygotecnologia.zygotv.showdetails.ShowDetails
import java.io.IOException
import kotlin.jvm.Throws

interface ShowDetailsRepository {
    @Throws(IOException::class)
    fun get(id: Int): ShowDetails
}