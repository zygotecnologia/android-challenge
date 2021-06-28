package com.zygotecnologia.zygotv.domain.entity

data class Genre(
    val id: Int?,
    val name: String?,
    var shows: List<Show>?
)