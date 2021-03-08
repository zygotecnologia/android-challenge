package com.zygotecnologia.zygotv.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.BuildConfig
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.network.TmdbApi

object ImageUrlBuilder {

    fun buildPosterUrl(posterPath: String): String {
        return BuildConfig.POSTER_URL + posterPath + "?${BuildConfig.TMDB_API_QUERY}=" + TmdbApi.TMDB_API_KEY
    }

    fun buildBackdropUrl(backdropPath: String): String {
        return BuildConfig.BACKDROP_URL + backdropPath + "?${BuildConfig.TMDB_API_QUERY}=" + TmdbApi.TMDB_API_KEY
    }

    fun String.loadPoster(itemView : View, imageView: ImageView)  =
        this.loadImage(itemView, imageView, ::buildPosterUrl)

    fun String.loadBackDrop(itemView : View, imageView: ImageView) =
        this.loadImage(itemView, imageView, ::buildBackdropUrl)

    fun String.loadImage(itemView : View, imageView: ImageView, urlBuilder : (path : String) -> String) {
        Glide.with(itemView)
            .load(urlBuilder(this))
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(imageView)
    }



}