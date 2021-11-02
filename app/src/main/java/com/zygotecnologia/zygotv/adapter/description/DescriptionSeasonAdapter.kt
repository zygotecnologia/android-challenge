package com.zygotecnologia.zygotv.adapter.description

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.RowSeasonItemBinding
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class DescriptionSeasonAdapter(
    private val seasonList: List<Season>
) : RecyclerView.Adapter<DescriptionSeasonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val seasonBinding = RowSeasonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(seasonBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(seasonList[position])
    }

    override fun getItemCount() = seasonList.size

    class ViewHolder(private val seasonView: RowSeasonItemBinding) :
        RecyclerView.ViewHolder(seasonView.root) {
        private var isShowingEpisodes = false

        fun bind(season: Season) {
            seasonView.seasonNumber.text = season.name
            seasonView.seasonDescription.text = season.overview
            seasonView.root.setOnClickListener {
                if (isShowingEpisodes) {
                    seasonView.episodesList.visibility = View.GONE
                    isShowingEpisodes = false
                } else {
                    seasonView.episodesList.visibility = View.VISIBLE
                    isShowingEpisodes = true
                }

            }

            Glide.with(itemView)
                .load(season.seasonPoster?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(seasonView.seasonPoster)

            seasonView.episodesList.apply {
                layoutManager = LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = season.episodes?.let { DescriptionEpisodeAdapter(it) }
            }
        }
    }
}