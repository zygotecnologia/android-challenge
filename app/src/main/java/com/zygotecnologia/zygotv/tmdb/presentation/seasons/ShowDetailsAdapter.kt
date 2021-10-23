package com.zygotecnologia.zygotv.tmdb.presentation.seasons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.EpisodeItemBinding
import com.zygotecnologia.zygotv.databinding.SeasonItemBinding
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowDetailsAdapter : ListAdapter<ShowDetailItem, RecyclerView.ViewHolder>(
    ShowDetailsDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.season_item -> createSeasonViewHolder(parent)
            R.layout.episode_item -> createEpisodeViewHolder(parent)
            else -> throw UnsupportedOperationException(
                "The view type $viewType is not supported by ShowDetailsAdapter."
            )
        }
    }

    private fun createSeasonViewHolder(parent: ViewGroup): SeasonViewHolder {
        val binding = SeasonItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(binding)
    }

    private fun createEpisodeViewHolder(parent: ViewGroup): EpisodeViewHolder {
        val binding = EpisodeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EpisodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is SeasonViewHolder -> holder.bind(item as ShowDetailItem.SeasonItem)
            is EpisodeViewHolder -> holder.bind(item as ShowDetailItem.EpisodeItem)
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is ShowDetailItem.SeasonItem -> R.layout.season_item
        is ShowDetailItem.EpisodeItem -> R.layout.episode_item
    }

    class SeasonViewHolder(
        private val binding: SeasonItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(seasonItem: ShowDetailItem.SeasonItem) {
            val season = seasonItem.season
            binding.seasonName.text = season.name
            binding.seasonOverview.text = season.overview

            val imageUrl = seasonItem.season.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            Glide.with(itemView)
                .load(imageUrl)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_placeholder)
                        .transform(MultiTransformation(CenterCrop(), RoundedCorners(16)))
                )
                .into(binding.poster)
        }
    }

    class EpisodeViewHolder(
        private val binding: EpisodeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(episodeItem: ShowDetailItem.EpisodeItem) {
            val episode = episodeItem.episode
            binding.episodeTitle.text = episode.name
            binding.episodeOverview.text = episode.overview
        }
    }

    class ShowDetailsDiffUtil : DiffUtil.ItemCallback<ShowDetailItem>() {

        override fun areItemsTheSame(
            oldItem: ShowDetailItem,
            newItem: ShowDetailItem
        ): Boolean {
            val isSameSeason = oldItem is ShowDetailItem.SeasonItem
                    && newItem is ShowDetailItem.SeasonItem
                    && oldItem.season.id == newItem.season.id

            val isSameEpisode = oldItem is ShowDetailItem.EpisodeItem
                    && newItem is ShowDetailItem.EpisodeItem
                    && oldItem.episode.id == newItem.episode.id

            return isSameSeason || isSameEpisode
        }

        override fun areContentsTheSame(
            oldItem: ShowDetailItem,
            newItem: ShowDetailItem
        ): Boolean = oldItem == newItem
    }
}
