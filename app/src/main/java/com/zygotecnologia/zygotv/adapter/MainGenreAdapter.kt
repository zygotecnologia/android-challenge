package com.zygotecnologia.zygotv.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.GenreItemRowBinding
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show

class MainGenreAdapter(
    private val genreList: List<Genre>,
    private val shows: List<Show>
) : RecyclerView.Adapter<MainGenreAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val genreBinding = GenreItemRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(genreBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genreList[position], shows)
    }

    override fun getItemCount() = genreList.size

    class ViewHolder(private val genreView: GenreItemRowBinding) :
        RecyclerView.ViewHolder(genreView.root) {

        fun bind(genre: Genre, shows: List<Show>) {
            val showListByGenre = shows.filter {
                it.genreIds?.contains(genre.id) == true
            }

            genreView.genreTitle.apply {
                text = genre.name
                rowVisibility(showListByGenre)
            }

            genreView.showRecycler.apply {
                layoutManager = LinearLayoutManager(
                    itemView.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
                adapter = MainShowAdapter(showListByGenre)
            }
        }

        private fun View.rowVisibility(showList: List<Show>) {
            this.isVisible = showList.isNotEmpty()
        }
    }
}