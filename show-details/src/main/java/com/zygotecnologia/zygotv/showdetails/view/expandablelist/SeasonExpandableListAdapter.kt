package com.zygotecnologia.zygotv.showdetails.view.expandablelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import com.zygotecnologia.zygotv.core.ImageLoader
import com.zygotecnologia.zygotv.showdetails.R
import com.zygotecnologia.zygotv.showdetails.view.SeasonViewModel
import kotlinx.android.synthetic.main.item_episode.view.*
import kotlinx.android.synthetic.main.item_season_header.view.*

class SeasonExpandableListAdapter : BaseExpandableListAdapter() {

    private var seasonViewModels = listOf<SeasonViewModel>()

    override fun getGroup(groupPosition: Int): Any {
        return seasonViewModels[groupPosition]
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        return (convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_season_header, null)).let { view ->
                seasonViewModels[groupPosition].let { seasonViewModel ->
                    view.seasonTitle.text = seasonViewModel.title
                    view.seasonOverview.text = seasonViewModel.overview
                    ImageLoader.loadImage(view.seasonImage, seasonViewModel.posterImageUrl)
                    view
                }
            }
    }

    override fun getChildrenCount(groupPosition: Int) = seasonViewModels[groupPosition].episodes.count()


    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return seasonViewModels[groupPosition].episodes[childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        return (convertView ?: LayoutInflater.from(parent.context)
            .inflate(R.layout.item_episode, null)).let { view ->
            seasonViewModels[groupPosition].episodes[childPosition].let { episodeViewModel ->
                view.title.text = episodeViewModel.title
                view.description.text = episodeViewModel.description
                view
            }
        }
    }

    override fun getChildId(groupPosition: Int, childPosition: Int) = childPosition.toLong()

    override fun getGroupCount() = seasonViewModels.count()

    override fun isChildSelectable(groupPosition: Int, childPosition: Int) = false

    override fun hasStableIds() = false

    fun setSeasons(seasonViewModels: List<SeasonViewModel>) {
        this.seasonViewModels = seasonViewModels
        notifyDataSetChanged()
    }
}