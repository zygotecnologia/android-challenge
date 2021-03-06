package com.zygotecnologia.zygotv.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.network.TmdbApi

object ImageUrlBuilder {

    private const val POSTER_URL = "https://image.tmdb.org/t/p/w154"
    private const val BACKDROP_URL = "https://image.tmdb.org/t/p/w780"

    fun buildPosterUrl(posterPath: String): String {
        return POSTER_URL + posterPath + "?${TmdbApi.TMDB_API_QUERY}=" + TmdbApi.TMDB_API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BACKDROP_URL + backdropPath + "?${TmdbApi.TMDB_API_QUERY}=" + TmdbApi.TMDB_API_KEY
    }

    fun String.loadImage(itemView : View, imageView: ImageView) {
        Glide.with(itemView)
            .load(buildPosterUrl(this))
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(imageView)
    }

}