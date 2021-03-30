package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.GenreAndShows
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails

class GenreAndShowAdapter(private val list: List<GenreAndShows>, clickedShow: ClickedShow) :
    RecyclerView.Adapter<GenreAndShowAdapter.ViewHolder>() {

    val clickedShow = clickedShow

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


    class ViewHolder(itemView: View, clickedShow: ClickedShow) : RecyclerView.ViewHolder(itemView), OnShowListener {

        lateinit var holderListOfShow: List<ShowDetails>
        private val holderClickedShow = clickedShow

        fun bind(genreAndShows: GenreAndShows) {
            val textView: TextView = itemView.findViewById(R.id.tv_genre_fragment_genre)
            textView.text = genreAndShows.genreName

            genreAndShows.listShow?.let {
                holderListOfShow = genreAndShows.listShow
                val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_show_list)
                recyclerView.adapter = MainAdapter(it, this)
            }
        }
        override fun onShowClick(position: Int) {
            holderClickedShow.show(holderListOfShow[position])
        }
    }
}