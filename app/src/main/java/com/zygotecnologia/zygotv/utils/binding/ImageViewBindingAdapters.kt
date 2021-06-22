package com.zygotecnologia.zygotv.utils.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

object ImageViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("backdropPath")
    fun ImageView.loadBackdrop(backdropPath: String?) {

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.image_placeholder)
            .centerCrop()

        Glide.with(context)
            .load(backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(requestOptions)
            .into(this)
    }
}