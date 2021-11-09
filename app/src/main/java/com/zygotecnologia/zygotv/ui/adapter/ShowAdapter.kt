package com.zygotecnologia.zygotv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowAdapter(
    private var showsList: List<Show>,
    private val clickListener: (data: Show) -> Unit
) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(showsList[position])
    }

    override fun getItemCount() = showsList.size

    class ViewHolder(
        itemView: View,
        private val showClickListener: (data: Show) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(show: Show) {

            val showTitle: TextView = itemView.findViewById(tv_show_title)
            showTitle.text = show.name

            val showImage: ImageView = itemView.findViewById(iv_show_poster)
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().centerCrop())
                .into(showImage)

            itemView.setOnClickListener {
                showClickListener(show)
                Toast.makeText(it.context, "click on ${show.id}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}