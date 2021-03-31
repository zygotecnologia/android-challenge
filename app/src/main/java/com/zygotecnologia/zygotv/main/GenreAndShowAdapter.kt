package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.GenreAndShows
import com.zygotecnologia.zygotv.model.Show

class GenreAndShowAdapter(
    private val list: List<GenreAndShows>,
    clickedShowShowAndGenre: OnClickShowAndGenre
) :
    RecyclerView.Adapter<GenreAndShowAdapter.ViewHolder>() {

    val clickedShow = clickedShowShowAndGenre

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view, clickedShow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    class ViewHolder(itemView: View, clickedShowShowAndGenre: OnClickShowAndGenre) :
        RecyclerView.ViewHolder(itemView), OnShowListener {

        lateinit var holderListOfShowDetails: List<Show>
        private val holderClickedShow = clickedShowShowAndGenre

        fun bind(genreAndShows: GenreAndShows) {
            val textView: TextView = itemView.findViewById(R.id.tv_genre_fragment_genre)
            val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_show_list)

            genreAndShows.listShowDetails?.let {
                    textView.text = genreAndShows.genreName
                    holderListOfShowDetails = genreAndShows.listShowDetails
                    recyclerView.adapter = MainAdapter(it, this)
            }
        }

        override fun onShowClick(position: Int) {
            holderClickedShow.onShowClick(holderListOfShowDetails[position])
        }
    }
}