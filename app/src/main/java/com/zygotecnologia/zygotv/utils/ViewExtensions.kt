package com.zygotecnologia.zygotv.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zygotecnologia.zygotv.R

fun ViewGroup.inflate(layoutId : Int) = LayoutInflater.from(context).inflate(layoutId, this, false)

fun View.gone() {
    if(visibility != View.GONE) {
        this.animate().alpha(0.0f);
        this.visibility = View.GONE
    }
}

fun View.visible() {
    if(visibility != View.VISIBLE) {
        visibility = View.VISIBLE
        animate().alpha(1.0f).setDuration(1000L);
    }
}

fun View.bindText(id : Int, text : CharSequence?) {
    text?.let {
        val tvEpisodeTitle: TextView = this.findViewById(id)
        tvEpisodeTitle.text = it
    }
}