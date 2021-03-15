package com.zygotecnologia.zygotv.presentation.season

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.ItemEpisodeBinding
import com.zygotecnologia.zygotv.service.remote.data.seasonsdetail.Episode

class EpisodeAdapter(private val episodeList: List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.EpisodeAdapterViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EpisodeAdapterViewHolder {
        return EpisodeAdapterViewHolder(
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    override fun getItemCount() = episodeList.size

    override fun onBindViewHolder(holder: EpisodeAdapterViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    class EpisodeAdapterViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            episode: Episode,
        ) {
            binding.textNameEpisode.text = episode.name
            binding.textSinopseEpisode.text = episode.overview
        }

    }


}
