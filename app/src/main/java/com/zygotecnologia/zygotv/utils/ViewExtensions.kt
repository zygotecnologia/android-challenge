package com.zygotecnologia.zygotv.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId : Int) : View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

fun View.gone() {
    this.animate().alpha(0.0f);
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
    this.animate().alpha(1.0f).setDuration(1200L);
}