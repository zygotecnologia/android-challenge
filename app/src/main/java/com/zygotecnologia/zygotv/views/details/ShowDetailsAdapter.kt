package com.zygotecnologia.zygotv.views.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.entity.Season
import com.zygotecnologia.zygotv.model.entity.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.views.main.ShowItemAdapter

class ShowDetailsAdapter(
    private val show: Show
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
       return when (position) {
            0 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_details_highlight_item, parent, false)
                HighlightVH(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_details_season_item, parent, false)
                SeasonVH(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HighlightVH) {
            holder.bind(show)
        } else if (holder is SeasonVH) {
            holder.bind(show.seasons?.get(position - 1)) {
                notifyItemChanged(position)
            }
        }
    }

    //Season count + highlight
    override fun getItemCount(): Int = (show.seasons?.size ?: 0) + 1

    class HighlightVH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(show: Show) {
            val title: TextView = itemView.findViewById(R.id.list_details_highlight_title)
            title.text = show.name

            val poster: ImageView = itemView.findViewById(R.id.list_details_highlight_poster)
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().centerCrop() )
                .into(poster)
        }
    }

    class SeasonVH(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(season: Season?, onToggleList: () -> Unit) {
            val title: TextView = itemView.findViewById(R.id.list_details_season_title)
            title.text = season?.name

            val description: TextView = itemView.findViewById(R.id.list_details_season_description)
            description.text = season?.overview

            val toggleButton: ImageView = itemView.findViewById(R.id.list_details_season_arrow)
            val toggleButtonDrawable = if (season?.showEpisodes == true) {
                R.drawable.ic_arrow_up
            } else {
                R.drawable.ic_arrow_down
            }
            toggleButton.setImageDrawable(ContextCompat.getDrawable(itemView.context, toggleButtonDrawable))

            toggleButton.setOnClickListener {
                season?.showEpisodes = !(season?.showEpisodes ?: false)
                onToggleList()
            }

            val episodesList:RecyclerView = itemView.findViewById(R.id.list_details_season_episodes_list)
            episodesList.adapter = EpisodeListAdapter(season?.episodes ?: emptyList())

            episodesList.visibility = if (season?.showEpisodes == true) {
                View.VISIBLE
            } else {
                View.GONE
            }

            val seasonPoster:ImageView = itemView.findViewById(R.id.list_details_season_poster)
            Glide.with(itemView)
                .load(season?.poster?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().centerCrop() )
                .into(seasonPoster)
        }
    }

}