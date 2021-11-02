package com.zygotecnologia.zygotv.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R

fun View.viewVisibility(isVisible: Boolean) {
    this.isVisible = isVisible
}

fun View.setBackEndImage(context: Context, imgUrl: String) {
    Glide.with(context)
        .load(ImageUrlBuilder.buildBackdropUrl(imgUrl) )
        .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
        .into(this as ImageView)
}