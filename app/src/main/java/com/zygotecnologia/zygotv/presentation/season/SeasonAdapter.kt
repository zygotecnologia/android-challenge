package com.zygotecnologia.zygotv.presentation.season

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.gone
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.common.visible
import com.zygotecnologia.zygotv.databinding.ItemSeasonBinding
import com.zygotecnologia.zygotv.service.remote.data.seasons.Season
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SeasonAdapter(private val onItemClicked: (Int, Boolean) -> Unit) :
    RecyclerView.Adapter<SeasonAdapter.ComedyAdapterViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    private lateinit var context: Context
    private val listItems = mutableListOf<Season>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComedyAdapterViewHolder {
        context = parent.context
        return ComedyAdapterViewHolder(
            ItemSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        )
    }

    fun addItems(newList: List<Season>) {
        val diffCallback = SeasonDiffCallback(listItems, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listItems.clear()
        listItems.addAll(newList)
        diffResult.dispatchUpdatesTo(this)

    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ComedyAdapterViewHolder, position: Int) {
        holder.bind(listItems[position], onItemClicked, position)
        changeVisibility(listItems[position].inVisibleDetails, holder)

        holder.binding.recyclerEpisodios.apply {

            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(getDrawable(context,R.drawable.divider)!!)
            addItemDecoration(itemDecoration)

            layoutManager =  LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = listItems[position].seasonDetails?.episodes?.let { EpisodeAdapter(it) }
            setRecycledViewPool(viewPool)
        }

    }

    class ComedyAdapterViewHolder(val binding: ItemSeasonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            season: Season,
            onItemClicked: (Int, Boolean) -> Unit,
            position: Int,
        ) {

            val url = season.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }

            binding.imageSeason.load(url)
            binding.textSeasonNumber.text = season.name
            binding.textSeasonSinopse.text = season.overview

            binding.root.setOnClickListener {
                onItemClicked.invoke(position, season.inVisibleDetails)
            }
        }


    }

    private fun changeVisibility(
        isVisibleDetails: Boolean,
        holder: ComedyAdapterViewHolder
    ) {
        if (isVisibleDetails) {
            holder.binding.recyclerEpisodios.visible()
            holder.binding.imageExpand.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_arrow_up
                )
            )
        } else {
            holder.binding.recyclerEpisodios.gone()
            holder.binding.imageExpand.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.ic_arrow_down
                )
            )
        }

    }

}
