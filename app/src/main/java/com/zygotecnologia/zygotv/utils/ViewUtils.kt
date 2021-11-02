package com.zygotecnologia.zygotv.utils

import android.view.View
import androidx.core.view.isVisible

fun View.viewVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}