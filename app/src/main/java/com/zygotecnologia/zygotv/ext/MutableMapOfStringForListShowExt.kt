package com.zygotecnologia.zygotv.ext

import com.zygotecnologia.zygotv.presentation.ui.home.item.parent.ParentItem
import com.zygotecnologia.zygotv.presentation.ui.home.item.parent.ParentType
import com.zygotecnologia.zygotv.data.model.show.Show
import com.zygotecnologia.zygotv.utils.Constants

fun MutableMap<String, MutableList<Show>>.toParentItem(): List<ParentItem> {
    return this.map {
        when (it.key) {
            Constants.favoriteKey -> {
                ParentItem(
                    it.value[0].originalName ?: "",
                    it.value.toChildItem(),
                    ParentType.Favorite
                )
            }
            else -> {
                it.value
                ParentItem(it.key, it.value.toChildItem())
            }
        }
    }
}