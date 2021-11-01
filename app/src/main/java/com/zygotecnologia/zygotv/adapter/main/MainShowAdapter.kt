package com.zygotecnologia.zygotv.adapter.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.RowShowItemBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.view.description.DescriptionActivity

class MainShowAdapter(
    private val shows: List<Show>
) : RecyclerView.Adapter<MainShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val genreBinding = RowShowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(genreBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount() = shows.size

    class ViewHolder(private val showView: RowShowItemBinding) :
        RecyclerView.ViewHolder(showView.root), View.OnClickListener {

        private lateinit var showInfo: Show

        fun bind(show: Show) {
            showInfo = show

            showView.showItem.setOnClickListener {
                onClick(it)
            }

            showView.tvShowTitle.text = show.name

            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(showView.ivShowPoster)
        }

        override fun onClick(v: View?) {
            showView.root.context.apply {
                startActivity(Intent(this, DescriptionActivity::class.java).apply {
                    putExtra(SHOW_ID_KEY, showInfo.id)
                })
            }
        }
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
    }
}