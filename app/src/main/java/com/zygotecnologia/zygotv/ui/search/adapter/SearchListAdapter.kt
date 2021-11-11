package com.zygotecnologia.zygotv.ui.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ItemRecyclerSeriesBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SearchListAdapter(private val context: Context, private val listSeries: List<Show>) :
    RecyclerView.Adapter<SearchListAdapter.SearchListViewHolder>() {

    class SearchListViewHolder(private val itemBinding: ItemRecyclerSeriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var titleSeries: TextView? = null
        var imageSeries: ShapeableImageView? = null

        init {
            titleSeries = itemBinding.itemRecyclerNameSeries
            imageSeries = itemBinding.itemRecyclerImage
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchListViewHolder {
        val itemBinding = ItemRecyclerSeriesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchListViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = listSeries.size

    override fun onBindViewHolder(holder: SearchListViewHolder, position: Int) {

        val url = listSeries[position].posterPath?.let { ImageUrlBuilder.buildBackdropUrl(it) }
        holder.titleSeries!!.text = listSeries[position].name

        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.image_placeholder)
            .into(holder.imageSeries as ImageView)
    }
}