package com.zygotecnologia.zygotv.main

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.inflate

class ShowAdapter(private val shows: List<Show>) : RecyclerView.Adapter<ShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.show_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(shows[position])

    override fun getItemCount() = shows.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(show: Show) {
            setupShowTitle(show)
            setupPoster(show)
            setItemViewClickListener(show)
        }

        private fun setupPoster(show: Show) {
            val imageView: ImageView = itemView.findViewById(iv_show_poster)
            show.posterPath?.loadImage(itemView, imageView)
        }

        private fun setupShowTitle(show: Show) {
            val textView: TextView = itemView.findViewById(tv_show_title)
            textView.text = show.name
        }

        private fun setItemViewClickListener(show: Show) {
            itemView.setOnClickListener { view ->
                val intent = Intent(view.context, DetailsActivity::class.java)
                intent.apply {
                    putExtra(DetailsActivity.ID_INTENT_EXTRA, show.id)
                    putExtra(DetailsActivity.TITLE_INTENT_EXTRA, show.name)
                    putExtra(DetailsActivity.BANNER_INTENT_EXTRA, show.backdropPath)
                    view.context.startActivity(this@apply)
                }
            }
        }
    }
}