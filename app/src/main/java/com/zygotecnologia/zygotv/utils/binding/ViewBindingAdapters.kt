package com.zygotecnologia.zygotv.utils.binding

import android.view.View
import androidx.databinding.BindingAdapter

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("show")
    fun View.show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }
}