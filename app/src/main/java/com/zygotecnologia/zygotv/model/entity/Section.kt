package com.zygotecnologia.zygotv.model.entity

enum class SectionType {
    HIGHLIGHT, LIST
}

data class Section(var title: String, var type: SectionType, var shows: List<Show>)