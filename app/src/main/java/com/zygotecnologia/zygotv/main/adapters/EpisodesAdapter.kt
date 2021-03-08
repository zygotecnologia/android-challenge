package com.zygotecnologia.zygotv.main.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Episode
import com.zygotecnologia.zygotv.utils.inflate

class EpisodesAdapter(private val episodes: List<Episode>) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.episodes_by_season_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(episodes[position])

    override fun getItemCount() = episodes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(episode: Episode) {
            setupTextViews(episode)
        }

        private fun setupTextViews(episode: Episode) {
            val tvEpisodeTitle: TextView = itemView.findViewById(R.id.tv_episode_title)
            tvEpisodeTitle.text = episode.name

            val tvEpisodeOverview: TextView = itemView.findViewById(R.id.tv_episode_overview)
            tvEpisodeOverview.text = episode.overview
        }

    }
}