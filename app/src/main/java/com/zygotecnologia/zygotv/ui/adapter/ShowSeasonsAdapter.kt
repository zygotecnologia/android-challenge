package com.zygotecnologia.zygotv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.ShowDetailsModel.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowSeasonsAdapter : RecyclerView.Adapter<ShowSeasonsAdapter.ViewHolder>() {

    private var seasonsList = listOf<Season>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.season_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasonsList[position])
    }

    override fun getItemCount() = seasonsList.size

    class ViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(season: Season) {
            val seasonTitle: TextView = itemView.findViewById(R.id.season_title)
            seasonTitle.text = season.name

            val seasonOverview: TextView = itemView.findViewById(R.id.season_overview)
            seasonOverview.text = season.overview

            val seasonImage: ImageView = itemView.findViewById(R.id.season_show_poster)
            Glide.with(itemView)
                .load(season.poster_path.let { ImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().centerCrop())
                .into(seasonImage)
        }
    }

    fun updateSeasonList(seasonList: List<Season>){
        seasonsList = seasonList
        notifyDataSetChanged()
    }
}