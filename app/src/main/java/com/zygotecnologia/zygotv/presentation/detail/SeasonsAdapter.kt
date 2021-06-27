package com.zygotecnologia.zygotv.presentation.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Episode
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SeasonsAdapter(
    private val context: Context,
    private val seasons: List<Season>
): BaseExpandableListAdapter() {

    override fun getGroupCount(): Int {
        return seasons.size
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return seasons[listPosition].episodes?.size ?: 0
    }

    override fun getGroup(listPosition: Int): Season {
        return seasons[listPosition]
    }

    override fun getChild(listPosition: Int, expandedListPosition: Int): Episode {
        return seasons[listPosition].episodes!![expandedListPosition]
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun hasStableIds() = false

    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        groupView: View?,
        parent: ViewGroup?
    ): View {

        var convertView = groupView
        if(convertView == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.season_list_item, null)
        }

        val season = seasons[listPosition]

        val seasonPosterImageView: ImageView = convertView!!.findViewById(R.id.ivSeasonPoster)
        Glide.with(convertView)
            .load(season.postPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(seasonPosterImageView)

        val expandedIndicator: ImageView = convertView.findViewById(R.id.ivArrow)

        if(isExpanded) {
            expandedIndicator.setImageResource(R.drawable.ic_arrow_up)
        } else {
            expandedIndicator.setImageResource(R.drawable.ic_arrow_down)
        }

        val seasonNumberTextView: TextView = convertView.findViewById(R.id.tvSeasonNumber)
        seasonNumberTextView.text = "Temporada ${listPosition + 1}"

        val synopsisTextView: TextView = convertView.findViewById(R.id.tvSeasonSynopsis)
        synopsisTextView.text = season.overview

        return convertView
    }

    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        groupView: View?,
        parent: ViewGroup?
    ): View {

        var convertView = groupView
        if(convertView == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.episode_list_item, null)
        }

        val episode = getChild(listPosition, expandedListPosition)

        val episodeNameTextView: TextView = convertView!!.findViewById(R.id.tvEpisodeName)
        episodeNameTextView.text = episode.name

        val episodeSynopsisTextView: TextView = convertView.findViewById(R.id.tvEpisodeSynopsis)
        episodeSynopsisTextView.text = episode.overview

        return convertView
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}