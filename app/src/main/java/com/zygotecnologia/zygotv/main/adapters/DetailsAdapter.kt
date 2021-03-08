package com.zygotecnologia.zygotv.main.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.expandablelayout.ExpandableLayout
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.utils.*
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadPoster

class DetailsAdapter(private val seasons: List<SeasonResponse>) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    companion object {
        const val NO_OVERVIEW_SEASON_MESSAGE = "<html><i>No overview found for this season</i></html>"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.details_item))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(seasons[position])

    override fun getItemCount() = seasons.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(season: SeasonResponse) {
            setupExpandable()
            setupHeader(season)
            setupDetails(season)
        }

        private fun setupDetails(season: SeasonResponse) {
            val rvEpisodesList: RecyclerView = itemView.findViewById(R.id.rv_episodes_list)
            rvEpisodesList.adapter = EpisodesAdapter(season.episodes ?: emptyList())
        }

        private fun setupHeader(season: SeasonResponse) {
            setupSeasonName(season.name)
            setupSeasonOverview(season.overview)
            setupSeasonPoster(season.posterPath)
        }

        private fun setupSeasonPoster(it: String?) {
            val ivPoster: ImageView = itemView.findViewById(R.id.iv_season_poster)
            it?.loadPoster(itemView, ivPoster)
        }

        private fun setupSeasonOverview(it: String?) =
            itemView.bindText(
                R.id.tv_season_overview,
                if (it.isNullOrEmpty()) NO_OVERVIEW_SEASON_MESSAGE.toHTML() else it
            )

        private fun setupSeasonName(it: String?) =
            itemView.bindText(R.id.tv_season_title, it)

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