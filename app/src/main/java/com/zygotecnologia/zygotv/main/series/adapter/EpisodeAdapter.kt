package com.zygotecnologia.zygotv.main.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.custom.ZygoTextfField
import com.zygotecnologia.zygotv.vo.seasondetail.Episode

class EpisodeAdapter (var episodes: List<Episode>) : RecyclerView.Adapter<EpisodeAdapter.EpisodeAdapterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.episode_adapter_layout, parent, false)
        return EpisodeAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeAdapterViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount(): Int = episodes.size

    inner class EpisodeAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind (episode : Episode) {
            val labelEpisodeName : ZygoTextfField = itemView.findViewById(R.id.label_episode_name)
            val labelEpisodeDescription : ZygoTextfField = itemView.findViewById(R.id.label_episode_description)

            labelEpisodeName.text = episode.name
            labelEpisodeDescription.text = episode.overview
        }

    }
}