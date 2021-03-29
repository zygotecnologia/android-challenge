package com.zygotecnologia.zygotv.views.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.entity.Episode

class EpisodeListAdapter(
    private val episodeList: List<Episode>
): RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_details_episode_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    //Season count + highlight
    override fun getItemCount(): Int = episodeList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(episode: Episode) {
            val title: TextView = itemView.findViewById(R.id.list_details_episode_title)
            title.text = episode.name

            val description: TextView = itemView.findViewById(R.id.list_details_episode_description)
            description.text = episode.overview
        }
    }

}