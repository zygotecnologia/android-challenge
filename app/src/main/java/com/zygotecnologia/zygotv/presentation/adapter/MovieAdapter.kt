package com.zygotecnologia.zygotv.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.domain.model.Genre
import kotlinx.android.synthetic.main.show_item.view.*


class MovieAdapter(private val genre: MutableList<Genre>) : RecyclerView.Adapter<GenreHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
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
    fun bind(genre: Genre) {
        itemView.txtGenre.text = genre.name
        itemView.recycler_genre.adapter = ShowAdapter(genre.movies)
        itemView.recycler_genre.layoutManager = LinearLayoutManager(
            parent.context, RecyclerView.HORIZONTAL, false
        )
    }
}
