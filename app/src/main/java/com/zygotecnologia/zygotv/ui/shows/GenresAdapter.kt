package com.zygotecnologia.zygotv.ui.shows

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HeaderShowViewBinding
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.ui.home.HomeFragmentDirections

class GenresAdapter(
    private val mostPopularShow: Show,
    private val genres: List<Genre>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 0
        const val VIEW_TYPE_GENRE_LIST = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_GENRE_LIST -> {
                val layout = LayoutInflater.from(parent.context).inflate(R.layout.genre_item, parent, false)
                GenreViewHolder(layout)
            }
            else -> {
                val binding = HeaderShowViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ShowHeaderViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            VIEW_TYPE_GENRE_LIST -> {
                (holder as GenreViewHolder).bind(genres[position-1])
            }
            else -> {
                (holder as ShowHeaderViewHolder).bind(mostPopularShow)
            }
        }
    }

    override fun getItemCount() = genres.size + 1

    override fun getItemViewType(position: Int) =
        when(position){
            0 -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_GENRE_LIST
        }

    class GenreViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(genre: Genre) {
            val textView: TextView = itemView.findViewById(R.id.tvGenre)
            textView.text = genre.name

            genre.shows?.let { shows ->
                val showsRecyclerView : RecyclerView = itemView.findViewById(R.id.rvShowList)
                showsRecyclerView.adapter = ShowsAdapter(shows)
            }
        }
    }

    class ShowHeaderViewHolder(
        val binding: HeaderShowViewBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(show: Show) {
            binding.apply {
                showName = show.name
                showBackdropPath = show.backdropPath
                show.id?.let { id ->
                    onClickAction = View.OnClickListener {
                        val direction =
                            HomeFragmentDirections.actionHomeFragmentToShowDetailFragment(id)
                        itemView.findNavController().navigate(direction)
                    }
                }
            }
        }
    }
}