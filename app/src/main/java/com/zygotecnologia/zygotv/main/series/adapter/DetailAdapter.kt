package com.zygotecnologia.zygotv.main.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.custom.ZygoTextfField
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.vo.Season
import com.zygotecnologia.zygotv.vo.seasondetail.SeasonDetail

class DetailAdapter(private val seasonDetail: List<Season>, private val episodesDetails : List<SeasonDetail?>) : RecyclerView.Adapter<DetailAdapter.DetailAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_adapter_layout, parent, false)
        return DetailAdapterViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailAdapterViewHolder, position: Int) {
        holder.bind(seasonDetail[position],episodesDetails[position]!!)
    }

    override fun getItemCount(): Int = seasonDetail.size

    inner class DetailAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(season: Season, episodes : SeasonDetail){
            val seasonId : ZygoTextfField = itemView.findViewById(R.id.label_season_id)
            val seasonDescription : ZygoTextfField = itemView.findViewById(R.id.label_season_description)
            val imgSeason : ImageView = itemView.findViewById(R.id.iv_thumb)
            val rvEpisodes : RecyclerView = itemView.findViewById(R.id.rv_episode)

            seasonId.text = season.name
            seasonDescription.text = season.overview
            rvEpisodes.adapter = EpisodeAdapter(episodes.episodes)

            Glide.with(itemView)
                .load(season.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(
                    RequestOptions().transforms(CenterInside(), RoundedCorners(8))
                    .placeholder(R.drawable.image_placeholder))
                .into(imgSeason)

        }
    }
}