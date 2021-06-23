package com.zygotecnologia.zygotv.ui.shows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show

class GenresAdapter(
    private val genres: List<Genre>
): RecyclerView.Adapter<GenresAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }

    override fun getItemCount() = genres.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(genre: Genre) {
            val textView: TextView = itemView.findViewById(R.id.tvGenre)
            textView.text = genre.name

            genre.shows?.let { shows ->
                val showsRecyclerView : RecyclerView = itemView.findViewById(R.id.rvShowList)
                showsRecyclerView.adapter = ShowsAdapter(shows)
            }
        }
    }
}