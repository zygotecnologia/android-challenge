package com.zygotecnologia.zygotv.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowsAdapter(
    private var shows: List<Show> = emptyList()
) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    fun updateShows(shows: List<Show>) {
        this.shows = shows
        notifyDataSetChanged()
    }

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