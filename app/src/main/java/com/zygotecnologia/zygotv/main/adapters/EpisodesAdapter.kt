package com.zygotecnologia.zygotv.main.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.episode.Episode
import com.zygotecnologia.zygotv.utils.bindText
import com.zygotecnologia.zygotv.utils.inflate
import com.zygotecnologia.zygotv.utils.toHTML


class EpisodesAdapter(private val episodes: List<Episode>) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    companion object {
        const val NO_OVERVIEW_EPISODE_MESSAGE = "<html><i>No overview fount for this Episode.</i></html"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.episodes_by_season_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(episodes[position])

    override fun getItemCount() = episodes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(episode: Episode) =
            setupTextViews(episode)

        private fun setupTextViews(episode: Episode) {
            setupEpisodeTitle(episode.name)
            setupEpisodeOverview(episode.overview)
        }

        private fun setupEpisodeOverview(overview: String?) =
            itemView.bindText(
                R.id.tv_episode_overview,
                if (overview?.isEmpty() != false) NO_OVERVIEW_EPISODE_MESSAGE.toHTML() else overview
            )

        private fun setupEpisodeTitle(name: String?) =
            itemView.bindText(R.id.tv_episode_title, name)

    }
}