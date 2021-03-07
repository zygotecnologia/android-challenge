package com.zygotecnologia.zygotv.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage

class ShowAdapter(private val shows: List<Show>) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(show: Show) {
            val textView: TextView = itemView.findViewById(tv_show_title)
            textView.text = show.name

            val imageView: ImageView = itemView.findViewById(iv_show_poster)
            show.posterPath?.loadImage(itemView, imageView)

            itemView.setOnClickListener {
                val intent = Intent(it.context, DetailsActivity::class.java)
                intent.putExtra("title", show.name)
                intent.putExtra("banner", show.backdropPath)
                it.context.startActivity(intent)
            }
        }
    }
}