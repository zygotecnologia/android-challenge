package com.zygotecnologia.zygotv.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Episode
import kotlinx.android.synthetic.main.episode_item.view.*

class EpisodeAdapter(val listEpisodes : List<Episode>) :
    RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(episode: Episode) {
            episode.overview.let { itemView.tv_episode_overview.text = it}
            episode.name.let{itemView.tv_episode_title.text = episode.name}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.episode_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(listEpisodes[position])
    }

    override fun getItemCount(): Int {
        return listEpisodes.size
    }
}