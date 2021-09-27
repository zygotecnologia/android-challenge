package com.zygotecnologia.zygotv.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.GenreSerie
import kotlinx.android.synthetic.main.genres_item.view.*

class GenreAndSeriesAdapter(private val listeGenreSerie: List<GenreSerie>) : RecyclerView.Adapter<GenreAndSeriesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(genreSerie: GenreSerie){
            val recyclerView: RecyclerView = itemView.findViewById(R.id.rv_series)
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            itemView.tv_genre.text = genreSerie.name
            recyclerView.adapter = SeriesAdapter(genreSerie.listSeries!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.genres_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(listeGenreSerie[position])
    }

    override fun getItemCount(): Int {
        return listeGenreSerie.size
    }
}