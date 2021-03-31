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
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowSearchedAdapter(
    private val showDetails: List<Show>,
    onCLickShowListener: OnCLickShowListener
) :
    RecyclerView.Adapter<ShowSearchedAdapter.ViewHolder>() {

    private val clickedShow = onCLickShowListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view, clickedShow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(showDetails[position])
    }

    override fun getItemCount(): Int = showDetails.size

    class ViewHolder(itemView: View, onCLickShowListener: OnCLickShowListener) :
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val holderClickedShow = onCLickShowListener

        fun bind(show: Show) {
            val titleTextView: TextView = itemView.findViewById(R.id.tv_show_title)
            val imgView: ImageView = itemView.findViewById(R.id.iv_show_poster)

            titleTextView.text = show.name
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imgView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(position: View?) {
            holderClickedShow.onShowClick(adapterPosition)
        }
    }
}