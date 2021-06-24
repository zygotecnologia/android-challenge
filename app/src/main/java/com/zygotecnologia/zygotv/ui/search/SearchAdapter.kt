package com.zygotecnologia.zygotv.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.ui.home.HomeFragmentDirections
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SearchAdapter(
    private val results: List<Show>
): RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_result_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    override fun getItemCount() = results.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(show: Show) {
            val textView: TextView = itemView.findViewById(R.id.tvSearchResultShowName)
            textView.text = show.name

            val imageView: ImageView = itemView.findViewById(R.id.ivSearchResultPoster)
            Glide.with(itemView)
                .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(imageView)

            show.id?.let { goToShowDetails(it) }
        }

        private fun goToShowDetails(showId: Int) {
            itemView.setOnClickListener {
                val direction = SearchFragmentDirections.actionSearchFragmentToShowDetailFragment(showId)
                itemView.findNavController().navigate(direction)
            }
        }
    }
}