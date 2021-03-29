package com.zygotecnologia.zygotv.views.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.entity.Section
import com.zygotecnologia.zygotv.model.entity.SectionType
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.utils.OnClickListener

class ShowItemAdapter(
    private val section: Section,
    private val showClickListener: OnClickListener<Show>
) : RecyclerView.Adapter<ShowItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = when(section.type) {
            SectionType.HIGHLIGHT -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_show_highlight_item, parent, false)
            }
            SectionType.LIST -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_show_default_item, parent, false)
            }
        }

        return ViewHolder(view, showClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(section.shows[position])
    }

    override fun getItemCount() = section.shows.size

    class ViewHolder(
        itemView: View,
        private val showClickListener: OnClickListener<Show>
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(show: Show) {
            itemView.setOnClickListener { showClickListener.onClick(show) }

            val text: TextView? = itemView.findViewById(R.id.list_show_name)
            text?.text = show.name

            val poster: ImageView = itemView.findViewById(R.id.list_show_poster)

            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().centerCrop() )
                .into(poster)
        }
    }
}