package com.zygotecnologia.zygotv.test

fun <T> createList(withItems: Int, build: (index: Int) -> T) = MutableList(withItems, build)
