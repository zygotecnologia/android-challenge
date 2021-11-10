package com.zygotecnologia.zygotv.ui.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ItemRecyclerSeasonsBinding
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SeasonRecyclerAdapter(
    private val context: Context,
    private val showDetails: ShowDetails
) : RecyclerView.Adapter<SeasonRecyclerAdapter.SeasonViewHolder>() {

    class SeasonViewHolder(private val itemBinding: ItemRecyclerSeasonsBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        var titleSeason: TextView? = null
        var synopsisSeason: TextView? = null
        var imageSeason: ShapeableImageView? = null

        init {
            titleSeason = itemBinding.itemRecyclerSeasons
            synopsisSeason = itemBinding.itemRecyclerSynopsis
            imageSeason = itemBinding.itemRecyclerImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val itemBinding =
            ItemRecyclerSeasonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {

        val url = showDetails.seasons?.get(position)?.poster_path.let { posterPath ->
            ImageUrlBuilder.buildBackdropUrl((posterPath ?: showDetails.backdrop_path)!!) }

        holder.titleSeason?.text = showDetails.seasons?.get(position)?.name

        var synopse = showDetails.seasons?.get(position)?.overview

        when {
            synopse!!.length >= 50 -> {
                synopse.substring(0, 50)
                synopse = "$synopse..."
                holder.synopsisSeason?.text = synopse
            }
            synopse == "" -> {
                holder.synopsisSeason?.text = context.getString(R.string.series_details_without_description)
            }
            else -> {
                holder.synopsisSeason?.text = synopse
            }
        }

        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.imageSeason as ImageView)
    }

    override fun getItemCount(): Int = showDetails.seasons?.size!!
}