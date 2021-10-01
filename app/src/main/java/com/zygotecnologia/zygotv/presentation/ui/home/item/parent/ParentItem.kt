package com.zygotecnologia.zygotv.presentation.ui.home.item.parent

import com.zygotecnologia.zygotv.presentation.ui.home.item.child.ChildItem


data class ParentItem(
    val title : String,
    val childItems : List<ChildItem>,
    val type : ParentType = ParentType.Child
)


