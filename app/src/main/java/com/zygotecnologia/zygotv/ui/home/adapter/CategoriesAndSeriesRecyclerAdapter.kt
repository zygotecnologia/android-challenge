package com.zygotecnologia.zygotv.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.ItemRercyclerCategoriesAndSeriesBinding
import com.zygotecnologia.zygotv.model.Show

class CategoriesAndSeriesRecyclerAdapter(
    private val context: Context,
    private val listSeries: List<Pair<String, List<Show>>>
) : RecyclerView.Adapter<CategoriesAndSeriesRecyclerAdapter.GenreViewHolder>() {

    var onClick: ((show: Show) -> Unit)? = null

    inner class GenreViewHolder(private val itemBinding: ItemRercyclerCategoriesAndSeriesBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        var titleSeries: TextView? = null
        var itemRecycler: RecyclerView

        init {
            titleSeries = itemBinding.itemGenre
            itemRecycler = itemBinding.itemRecyclerSeries
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemBinding = ItemRercyclerCategoriesAndSeriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GenreViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return listSeries.size
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.titleSeries!!.text = listSeries[position].first
        setCatItemRecycler(holder.itemRecycler, listSeries[position].second)
    }

    private fun setCatItemRecycler(
        recyclerView: RecyclerView,
        listSeries: List<Show>
    ) {
        val itemRecyclerAdapter = SeriesRecyclerAdapter(context, listSeries).apply {
            onItemClick = {
                onClick?.invoke(it)
            }
        }
        recyclerView.run {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = itemRecyclerAdapter
        }
    }
}