package com.zygotecnologia.zygotv.tmdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.tmdb.domain.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowsAdapter : ListAdapter<Show, ShowsAdapter.ViewHolder>(ShowDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(showResponse: Show) {
            binding.showTitle.text = showResponse.name

            val imageUrl = showResponse.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            Glide.with(itemView)
                .load(imageUrl)
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.showPoster)
        }
    }

    class ShowDiffUtil : DiffUtil.ItemCallback<Show>() {

        override fun areItemsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean = oldItem == newItem
    }
}