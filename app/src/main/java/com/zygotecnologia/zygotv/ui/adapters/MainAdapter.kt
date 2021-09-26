package com.zygotecnologia.zygotv.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowClicked
import com.zygotecnologia.zygotv.databinding.ItemListShowBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class MainAdapter(
    private val shows: List<Show>,
    private val listener: ShowClicked
    ) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder.inflateViewBinding(parent, viewType)
        return ViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    class ViewHolder(private val binding: ItemListShowBinding, private val listener: ShowClicked) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            internal fun inflateViewBinding(parent: ViewGroup, viewType: Int): ItemListShowBinding {
                return ItemListShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }

        fun bind(show: Show) {

            binding.tvShowTitle.text = show.name

            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.ivShowPoster)

                binding.ivShowPoster.setOnClickListener {
                    show.id?.let { id -> listener.onItemClick(id) }
                }
        }
    }
}