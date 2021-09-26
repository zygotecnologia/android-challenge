package com.zygotecnologia.zygotv.ui.adapters

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.Season
import com.zygotecnologia.zygotv.databinding.ItemListSeasonsBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SeasonAdapter(
    private val seasons: MutableList<Season>,
) : RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder.inflateViewBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasons[position])
    }

    override fun getItemCount() = seasons.size

    class ViewHolder(private val binding: ItemListSeasonsBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            internal fun inflateViewBinding(parent: ViewGroup, viewType: Int): ItemListSeasonsBinding {
                return ItemListSeasonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }

        fun bind(season: Season) {
            binding.data = season

            val list = season.episodes?.sortedBy { it.episodeNumber }?.toMutableList()

            Glide.with(itemView)
                .load(season.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(binding.ivShowPoster)

            binding.rvEpisodes.also{
                it.adapter = list?.let { it1 -> EpisodesAdapter(it1) }
            }

            binding.root.setOnClickListener {
                showListOfEpisodes(binding.rvEpisodes.isVisible)
            }
        }

        private fun showListOfEpisodes(boolean: Boolean){
            if(!boolean){
                binding.ivExpandMore.visibility = View.INVISIBLE
                binding.ivExpandLess.visibility = View.VISIBLE
                binding.rvEpisodes.visibility =  View.VISIBLE
            }else{
                binding.ivExpandMore.visibility = View.VISIBLE
                binding.ivExpandLess.visibility = View.INVISIBLE
                binding.rvEpisodes.visibility =  View.GONE
            }
        }
    }
}