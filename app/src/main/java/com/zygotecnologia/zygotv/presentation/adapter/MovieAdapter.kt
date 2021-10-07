package com.zygotecnologia.zygotv.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.network.TmdbApi
import com.zygotecnologia.zygotv.domain.model.Genre
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.presentation.activity.DetailActivity
import kotlinx.android.synthetic.main.item_show.view.*
import kotlinx.android.synthetic.main.show.view.*


class MovieAdapter(
    private val genre: MutableList<Genre>,
    private val clickListener: (Show) -> Unit
) : RecyclerView.Adapter<GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show, parent, false)
        return GenreHolder(view, parent)

    }

    override fun onBindViewHolder(holder: GenreHolder, position: Int) {

        val genre2 = genre[position]
        holder.bind(genre2)
    }

    override fun getItemCount(): Int = genre.size
}

class GenreHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
    val parent = parent


    fun bind(genre: Genre): ImageView? {
        itemView.txtGenre.text = genre.name

        itemView.rvGenre.adapter = ShowAdapter(genre.movies, clickListener = {
            handleClick(it,parent)
        })
        itemView.rvGenre.layoutManager = LinearLayoutManager(
            parent.context, RecyclerView.HORIZONTAL, false
        )
        return itemView.img_show

    }
}

private fun handleClick(movie: Show,parent: ViewGroup) {
    val intent = Intent(parent.context, DetailActivity::class.java)
    intent.putExtra("originalName", movie.original_name)
    intent.putExtra("poster",  TmdbApi.TMDB_BASE_IMAGE_URL + movie.posterPath)
    intent.putExtra("name", movie.name)
    intent.putExtra("backdropPath",  TmdbApi.TMDB_BASE_IMAGE_URL +movie.backdropPath)
    intent.putExtra("genre", movie.overview)
    parent.context.startActivity(intent)
}
