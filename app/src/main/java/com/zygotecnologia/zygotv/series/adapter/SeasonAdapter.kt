package com.zygotecnologia.zygotv.series.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.android.synthetic.main.serie_details_item.view.*

class SeasonAdapter(private val listSeason: List<Season>, context: Context) :
    RecyclerView.Adapter<SeasonAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(season: Season) {
            if (season.seasonNumber == 0) itemView.tv_title_season_details.text = season.name
            else itemView.tv_title_season_details.text = "Temporada ${season.seasonNumber}"
            var shortOverview = season.overview
            if (shortOverview!!.length > 100) {
                shortOverview = shortOverview.subSequence(0, 100).toString() + "..."
            }
            itemView.tv_overview_season_details.text = shortOverview
            Glide.with(itemView.context)
                .load(season?.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(itemView.iv_season_details)
            itemView.rv_episodes.layoutManager = LinearLayoutManager(itemView.context)
            if (!season.episodes.isNullOrEmpty()) itemView.rv_episodes.adapter =
                EpisodeAdapter(season.episodes!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.serie_details_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(listSeason[position])
        holder.itemView.setOnClickListener {
            if (it.rv_episodes.visibility == VISIBLE) {
                it.rv_episodes.visibility = GONE
                it.cb_collapse_episodes.isChecked = true
            }
            else {
                it.rv_episodes.visibility = VISIBLE
                it.cb_collapse_episodes.isChecked = false
            }

        }
    }

    override fun getItemCount(): Int {
        return listSeason.size
    }
}