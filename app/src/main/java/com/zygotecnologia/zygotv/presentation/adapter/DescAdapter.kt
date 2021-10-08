package com.zygotecnologia.zygotv.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.domain.model.Show
import kotlinx.android.synthetic.main.item_show.view.*

class DescAdapter(private val shows: MutableList<Show>, private val clickListener: (Show) -> Unit) :
    RecyclerView.Adapter<DescHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_detail2,
            parent,
            false
        )
        return DescHolder(view)
    }

    override fun onBindViewHolder(holder: DescHolder, position: Int) {
        val show = shows[position]
            holder.bind2(show)

    }
    override fun getItemCount(): Int = shows.size
}

class DescHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind2(show: Show) {

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


