package com.zygotecnologia.zygotv.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show

class MainGenreAdapter(
    private val genreList: List<Genre>,
    private val shows: List<Show>
) : RecyclerView.Adapter<MainGenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.genre_item_row,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genreList[position], shows)
    }

    override fun getItemCount() = genreList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreTitle: TextView by lazy { itemView.findViewById(R.id.genre_title) }
        private val showList: RecyclerView by lazy { itemView.findViewById(R.id.show_recycler) }

        fun bind(genre: Genre, shows: List<Show>) {
            val showListByGenre = shows.filter {
                it.genreIds?.contains(genre.id) == true
            }

            genreTitle.apply {
                text = genre.name
                rowVisibility(showListByGenre)
            }

            showList.apply {
                layoutManager = LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = MainShowAdapter(showListByGenre)
            }
        }

        private fun View.rowVisibility(showList : List<Show>) {
            this.isVisible = showList.isNotEmpty()
        }
    }
}