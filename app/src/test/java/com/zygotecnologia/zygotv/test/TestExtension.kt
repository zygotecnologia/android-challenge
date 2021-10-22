package com.zygotecnologia.zygotv.test

import com.zygotecnologia.zygotv.main.data.source.remote.retrofit.networkresult.NetworkResult

fun <T> createList(withItems: Int, build: (index: Int) -> T) = MutableList(withItems, build)

fun <T> T.asSuccess() = NetworkResult.Success(this)
