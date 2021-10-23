package com.zygotecnologia.zygotv.tmdb.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ShowItemBinding
import com.zygotecnologia.zygotv.tmdb.domain.Show
import com.zygotecnologia.zygotv.utils.toTmdbPosterUrl

class ShowsAdapter(
    private val onShowClicked: ((Show) -> Unit)? = null
) : ListAdapter<Show, ShowsAdapter.ViewHolder>(ShowDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ViewHolder(binding)

        if (onShowClicked != null) binding.root.setOnClickListener {
            val show = getItem(holder.bindingAdapterPosition)
            onShowClicked.invoke(show)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ShowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(show: Show) {
            binding.showTitle.text = show.name

            Glide.with(itemView)
                .load(show.posterPath.toTmdbPosterUrl())
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.image_placeholder)
                        .transform(RoundedCorners(16))
                )
                .into(binding.showPoster)
        }
    }

    class ShowDiffUtil : DiffUtil.ItemCallback<Show>() {

        override fun areItemsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean = oldItem == newItem
    }
}