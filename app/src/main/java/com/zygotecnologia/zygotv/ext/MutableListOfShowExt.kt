package com.zygotecnologia.zygotv.ext

import com.zygotecnologia.zygotv.presentation.ui.home.item.child.ChildItem
import com.zygotecnologia.zygotv.data.model.show.Show

fun MutableList<Show>.toChildItem(): List<ChildItem> {
    return this.map {
        ChildItem(
            it.originalName ?: "",
            it.posterPath ?: "", it.overview ?: "",
            it.backdropPath ?: "",
            it.id ?: 0
        )
    }
}