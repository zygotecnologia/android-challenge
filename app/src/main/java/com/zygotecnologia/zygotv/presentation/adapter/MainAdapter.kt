package com.zygotecnologia.zygotv.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

const val HEADER_TYPE = 0
const val BODY_TYPE = 1
class MainAdapter(
    private val mostPopular: Show?,
    private val genres: List<Genre>,
    private val showsList:  Map<Genre, List<Show>>,
    private val listener: (Show) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
                HEADER_TYPE -> HeadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.header_show_item, parent, false))
                else -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.gender_show_list, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HeadViewHolder) {
            holder.bind(mostPopular)
        } else {
            (holder as ViewHolder).bind(genres[position], showsList[genres[position]], listener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) HEADER_TYPE else BODY_TYPE
    }

    override fun getItemCount() = genres.size

    class HeadViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(show: Show?) {
            val textView: TextView = itemView.findViewById(tv_show_title)
            textView.text = show?.name

            val imageView: ImageView = itemView.findViewById(iv_show_poster)
            Glide.with(itemView)
                .load(show?.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imageView)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(genre: Genre, shows: List<Show>?, listener: (Show) -> Unit) {
            val viewPool = RecyclerView.RecycledViewPool()
            val title: TextView = itemView.findViewById(R.id.tv_show_title)
            val recyclerView : RecyclerView = itemView.findViewById(R.id.recyclerView)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                shows?.let { adapter = GenderAdapter(shows, listener) }
                setRecycledViewPool(viewPool)
            }
            title.text = genre.name
        }
    }
}