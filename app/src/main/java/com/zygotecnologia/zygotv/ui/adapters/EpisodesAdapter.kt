package com.zygotecnologia.zygotv.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.data.model.Episode
import com.zygotecnologia.zygotv.databinding.ItemListEpisodesBinding

class EpisodesAdapter(
    private val episodes: MutableList<Episode>,
) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewHolder.inflateViewBinding(parent, viewType)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodes[position])
    }

    override fun getItemCount() = episodes.size

    class ViewHolder(private val binding: ItemListEpisodesBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {
            internal fun inflateViewBinding(parent: ViewGroup, viewType: Int): ItemListEpisodesBinding {
                return ItemListEpisodesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }

        fun bind(episode: Episode) {
            binding.data = episode
        }
    }
}