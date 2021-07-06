package com.zygotecnologia.zygotv.core

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

object ImageLoader {

    fun loadImage(imageView: ImageView, url: String, placeholder: Drawable? = null) {
        Glide.with(imageView)
            .load(url)
            .apply(RequestOptions().placeholder(placeholder))
            .into(imageView)
    }
}