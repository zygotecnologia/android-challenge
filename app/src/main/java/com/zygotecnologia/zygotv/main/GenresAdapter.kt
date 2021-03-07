package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show

class GenresAdapter(private val genres: List<Genre>, private val shows: List<Show>) : RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shows_by_genre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position], shows)
    }

    override fun getItemCount() = genres.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(genre: Genre, shows: List<Show>) {
            val textView: TextView = itemView.findViewById(R.id.tv_genre_name)
            textView.text = genre.name

            val showsByGenre = shows.filter { it.genreIds!!.contains(genre.id) }
            val rvShowList: RecyclerView = itemView.findViewById(R.id.rv_show_list)
            rvShowList.adapter = ShowAdapter(showsByGenre)

        }

    }
}