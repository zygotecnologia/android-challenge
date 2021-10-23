package com.zygotecnologia.zygotv.tmdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.GenreItemBinding
import com.zygotecnologia.zygotv.tmdb.domain.genre.GenreWithShows
import com.zygotecnologia.zygotv.tmdb.domain.show.Show

class GenresAdapter(
    private val onShowClicked: ((Show) -> Unit)? = null
) : ListAdapter<GenreWithShows, GenresAdapter.ViewHolder>(
    GenreWithShowsDiffUtil()
) {

    private val sharedViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GenreItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        setNestedRecyclerView(
            binding.showRecycler,
            ShowsAdapter(onShowClicked)
        )

        return ViewHolder(binding)
    }

    private fun setNestedRecyclerView(
        recyclerView: RecyclerView,
        recyclerAdapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>
    ) {
        recyclerView.apply {
            setRecycledViewPool(sharedViewPool)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = recyclerAdapter
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: GenreItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(genreWithShows: GenreWithShows) {
            binding.genreText.text = genreWithShows.genre.name
            getNestedAdapter(binding).submitList(genreWithShows.shows)
        }
    }

    private fun getNestedAdapter(binding: GenreItemBinding): ShowsAdapter {
        val recyclerView = binding.showRecycler
        return recyclerView.adapter as ShowsAdapter
    }

    class GenreWithShowsDiffUtil : DiffUtil.ItemCallback<GenreWithShows>() {

        override fun areItemsTheSame(
            oldItem: GenreWithShows,
            newItem: GenreWithShows
        ): Boolean = oldItem.genre.id == newItem.genre.id

        override fun areContentsTheSame(
            oldItem: GenreWithShows,
            newItem: GenreWithShows
        ): Boolean = oldItem == newItem
    }
}