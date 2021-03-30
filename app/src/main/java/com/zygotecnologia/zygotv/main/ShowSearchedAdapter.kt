package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ShowSearchedAdapter(private val shows: List<ShowDetails>) :
    RecyclerView.Adapter<ShowSearchedAdapter.ViewHolder>() {

    /*private var showsList = shows
    private val showsListFilter = ArrayList<ShowDetails>(showsList)*/

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])
    }

    override fun getItemCount(): Int = shows.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(show: ShowDetails) {
            val titleTextView: TextView = itemView.findViewById(R.id.tv_show_title)
            val imgView: ImageView = itemView.findViewById(R.id.iv_show_poster)

            titleTextView.text = show.name
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imgView)
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            TODO("Not yet implemented")
        }
    }

   /* override fun getFilter(): Filter {
        return showsFiltered
    }

    private val showsFiltered = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: MutableList<ShowDetails> = mutableListOf()

            if (constraint == null || constraint.isEmpty()) {
                filteredList.addAll(showsListFilter.toMutableList())
            } else {
                val filterPattern: String = constraint.toString().toLowerCase().trim()

                for (item in showsListFilter) {
                    if (item.name.toString().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        override fun publishResults(p0: CharSequence?, results: FilterResults?) {
            showsList.clear()
            showsList.addAll(results?.values as MutableList<ShowDetails>)
        }


    }*/
}