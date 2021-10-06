package com.zygotecnologia.zygotv.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.domain.model.Show
import kotlinx.android.synthetic.main.show.view.*

class ShowAdapter(private val shows: MutableList<Show>) : RecyclerView.Adapter<ShowHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show, parent, false)
        return ShowHolder(view)
    }

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        val show = shows[position]
        holder.bind(show)
    }

    override fun getItemCount(): Int = shows.size
}

class ShowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(show: Show) {

        if (show.posterPath !== "") {
            val url = TmdbApi.TMDB_BASE_IMAGE_URL + show.posterPath
            itemView.img_show?.let {
                Glide.with(itemView.img_show.context).load(
                    url
                ).into(it)
            }
        }
    }
}


