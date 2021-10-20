package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class MainAdapter(private val shows: List<Show>) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    class ViewHolder(
        private val binding: ShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(show: Show) {
            binding.tvShowTitle.text = show.name

            val imageUrl = show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            Glide.with(itemView)
                .load(imageUrl)
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.ivShowPoster)
        }
    }
}