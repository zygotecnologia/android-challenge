package com.zygotecnologia.zygotv.common

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R


fun ImageView.load(url: String?) {
    var requestOptions = RequestOptions()
    requestOptions = requestOptions.transform(RoundedCorners(7))
    if (!url.isNullOrBlank()) {
        Glide.with(this)
            .load(url)
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .apply(requestOptions)
            .into(this)
    } else {
//        this.setImageResource(R.drawable.no_image)
    }
}

