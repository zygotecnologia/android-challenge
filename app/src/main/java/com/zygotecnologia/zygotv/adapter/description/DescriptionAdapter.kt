package com.zygotecnologia.zygotv.adapter.description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.SeasonItemRowBinding
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class DescriptionAdapter(
    private val seasonList: List<Season>
) : RecyclerView.Adapter<DescriptionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val seasonBinding = SeasonItemRowBinding.inflate(
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

    class ViewHolder(val seasonView: SeasonItemRowBinding) :
        RecyclerView.ViewHolder(seasonView.root) {

        fun bind(season: Season) {
            seasonView.seasonNumber.text = season.name
            seasonView.seasonDescription.text = season.overview

            Glide.with(itemView)
                .load(seasonView.seasonPoster.let { season.seasonPoster?.let { it1 ->
                    ImageUrlBuilder.buildPosterUrl(
                        it1
                    )
                } })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(seasonView.seasonPoster)
        }
    }
}