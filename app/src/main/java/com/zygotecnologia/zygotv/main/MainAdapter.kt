package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class MainAdapter(private val showDetails: List<Show>, onShowListener: OnShowListener) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private val onShowListener = onShowListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view, onShowListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(showDetails[position])
    }

    override fun getItemCount() = showDetails.size

    class ViewHolder(itemView: View, onShowListener: OnShowListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val onHolderShowListener = onShowListener

        fun bind(show: Show) {
            val textView: TextView = itemView.findViewById(tv_show_title)
            textView.text = show.name

            val imageView: ImageView = itemView.findViewById(iv_show_poster)
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imageView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onHolderShowListener.onShowClick(adapterPosition)
        }
    }
}
interface OnShowListener {
    fun onShowClick(position: Int)
}