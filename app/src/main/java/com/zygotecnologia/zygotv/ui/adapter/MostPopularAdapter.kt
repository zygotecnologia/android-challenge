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
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class MostPopularAdapter :RecyclerView.Adapter<MostPopularAdapter.ViewHolder>() {

    private var mostPopular: Show? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.featured_show, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(mostPopular)
    }

    override fun getItemCount() = 1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(mostPopularShow: Show?) {
            val mostPopularName: TextView = itemView.findViewById(R.id.featured_show_name)
            mostPopularName.text = mostPopularShow?.name

            val mostPopularImage: ImageView = itemView.findViewById(R.id.featured_show_poster)
            Glide.with(itemView)
                .load(mostPopularShow?.posterPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().centerCrop())
                .into(mostPopularImage)
        }
    }

    fun updateMostPopularShow(mostPopularShow: Show) {
        mostPopular = mostPopularShow
        notifyDataSetChanged()
    }
}