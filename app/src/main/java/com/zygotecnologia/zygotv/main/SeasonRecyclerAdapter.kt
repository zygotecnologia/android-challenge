package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SeasonRecyclerAdapter(private val seasonList: List<Season>) :
    RecyclerView.Adapter<SeasonRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_seleleted_show, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasonList[position])
    }

    override fun getItemCount(): Int = seasonList.size

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(season: Season) {
            val titleTextView: TextView =
                itemView.findViewById(R.id.tv_season_name_item_seletected_item)
            titleTextView.text = season.name

            val sinpseTextView: TextView =
                itemView.findViewById(R.id.tv_sinopse_item_seletected_item)
            sinpseTextView.text = season.overview

            val imagemSeasonView: ImageView = itemView.findViewById(R.id.img_item_selected_item)
            Glide.with(itemView)
                .load(season.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imagemSeasonView)
        }
    }
}