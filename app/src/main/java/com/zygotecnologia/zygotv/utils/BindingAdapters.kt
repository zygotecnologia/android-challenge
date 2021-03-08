package com.zygotecnologia.zygotv.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.main.GenreAdapter
import com.zygotecnologia.zygotv.main.ShowDetailedAdapter
import com.zygotecnologia.zygotv.model.GenreCategory
import com.zygotecnologia.zygotv.model.RequestStatus
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show

@BindingAdapter("imagemURL")
fun bindImagem(imagemView: ImageView, posterPath: String?) {
    posterPath?.let {
        Glide.with(imagemView.context)
            .load(ImageUrlBuilder.buildPosterUrl(posterPath))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imagemView)
    }
}

@BindingAdapter("imagemURLBackDrop")
fun bindImagemBackDrop(imagemView: ImageView, posterPath: String?) {
    posterPath?.let {
        Glide.with(imagemView.context)
            .load(ImageUrlBuilder.buildBackdropUrl(posterPath))
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imagemView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GenreCategory>?) {
    data?.let {
        val adapter = recyclerView.adapter as GenreAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("listDataSeason")
fun bindRecyclerViewSeason(recyclerView: RecyclerView, data: List<Season>?) {
    data?.let {
        val adapter = recyclerView.adapter as ShowDetailedAdapter
        adapter.submitList(data)
    }
}

@BindingAdapter("statusRequisicao")
fun bindStatusRequisicao(statusImageView: ImageView, status: RequestStatus?) {
    when (status) {
        RequestStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        RequestStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        RequestStatus.SUCCESS -> {
            statusImageView.visibility = View.GONE
        }
    }
}