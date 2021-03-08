package com.zygotecnologia.zygotv.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.ShowDetailedItemBinding
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.viewmodel.MoviesViewModel
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.ext.android.viewModel


class ShowDetailedAdapter(
    private val viewModel: MoviesViewModel,
    private var onClickListener: OnClickListener
) : ListAdapter<Season, ShowDetailedAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowDetailedAdapter.ViewHolder {
        return ShowDetailedAdapter.ViewHolder(
            ShowDetailedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShowDetailedAdapter.ViewHolder, position: Int) {
        val show: Season = getItem(position)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(show)
        }

        holder.bind(show, viewModel)
    }


    class OnClickListener(val clickListener: (show: Season) -> Unit) {
        fun onClick(show: Season) = clickListener(show)
    }

    class ViewHolder(private var binding: ShowDetailedItemBinding): RecyclerView.ViewHolder(binding.root) {
        private var searchJob: Job? = null

        fun bind(
            show: Season,
            viewModel: MoviesViewModel,
        ){
            binding.viewModel = show
            binding.button.setOnClickListener {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.searchEpisode(show.id, show.season_number)
                }
            }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Season>(){
        override fun areItemsTheSame(
            oldItem: Season,
            newItem: Season
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Season,
            newItem: Season
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}