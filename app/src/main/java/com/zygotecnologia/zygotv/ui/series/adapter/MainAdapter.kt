package com.zygotecnologia.zygotv.ui.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.iv_show_poster
import com.zygotecnologia.zygotv.R.id.tv_show_title
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import java.util.*
import kotlin.collections.ArrayList

class MainAdapter constructor(private var shows: List<Show>, val itemClickListener: (Show) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>(), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
        holder.content.setOnClickListener { itemClickListener(shows[position]) }
    }

    override fun getItemCount() = shows.size

    fun filterDataSet(textFilter : String){
        shows.filter { it.name!!.contains(textFilter) }
        notifyDataSetChanged()

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val content : ConstraintLayout = itemView.findViewById(R.id.cl_main)
        fun bind(show: Show) {
            val textView: TextView = itemView.findViewById(tv_show_title)
            textView.text = show.name

            val imageView: ImageView = itemView.findViewById(iv_show_poster)
            Glide.with(itemView)
                    .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                    .apply(RequestOptions().transforms(CenterInside(), RoundedCorners(8))
                    .placeholder(R.drawable.image_placeholder))
                    .into(imageView)


        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    shows = shows
                } else {
                    val resultList = ArrayList<Show>()
                    for (row in shows) {
                        if (row.name?.toLowerCase(Locale.ROOT)!!.contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    shows = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = shows
                return filterResults

            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                shows = results?.values as ArrayList<Show>
                notifyDataSetChanged()
            }

        }
    }
}