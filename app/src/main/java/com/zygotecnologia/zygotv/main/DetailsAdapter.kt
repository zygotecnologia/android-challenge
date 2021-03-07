package com.zygotecnologia.zygotv.main

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.expandablelayout.ExpandableLayout
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.gone
import com.zygotecnologia.zygotv.utils.inflate
import com.zygotecnologia.zygotv.utils.toHTML
import com.zygotecnologia.zygotv.utils.visible

class DetailsAdapter(private val seasons: List<Season>) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.details_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(seasons[position])

    override fun getItemCount() = seasons.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(season: Season) {
            setupExpandable()
            setupHeader(season)
            setupDetails(season)
        }

        private fun setupDetails(season: Season) {

        }

        private fun setupHeader(season: Season) {
            val tvSeasonTitle: TextView = itemView.findViewById(R.id.tv_season_title)
            tvSeasonTitle.text = season.name

            val tvSeasonOverview: TextView = itemView.findViewById(R.id.tv_season_overview)
            season.overview?.let {
                tvSeasonOverview.text = if (it.isEmpty()) "<html><i>No overview found for this season</i></html>".toHTML() else it
            }

            val ivPoster : ImageView = itemView.findViewById(R.id.iv_season_poster)
            season.posterPath?.loadImage(itemView, ivPoster)
        }

        private fun setupExpandable() {
            val expandable: ExpandableLayout = itemView.findViewById(R.id.expandable)
            expandable.run {
                setOnClickListener {
                    if (isExpanded) {
                        collapse()
                        secondLayout.gone()
                    } else {
                        expand()
                        secondLayout.visible()
                    }
                }
            }

        }


    }
}