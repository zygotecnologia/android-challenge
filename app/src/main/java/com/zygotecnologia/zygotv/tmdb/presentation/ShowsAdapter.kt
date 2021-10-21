package com.zygotecnologia.zygotv.tmdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.tmdb.data.source.remote.dto.ShowResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowsAdapter(
    private var showResponses: List<ShowResponse> = emptyList()
) : RecyclerView.Adapter<ShowsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(showResponses[position])
    }

    override fun getItemCount() = showResponses.size

    fun updateShows(showResponses: List<ShowResponse>) {
        this.showResponses = showResponses
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(showResponse: ShowResponse) {
            binding.tvShowTitle.text = showResponse.name

            val imageUrl = showResponse.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            Glide.with(itemView)
                .load(imageUrl)
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.ivShowPoster)
        }
    }
}