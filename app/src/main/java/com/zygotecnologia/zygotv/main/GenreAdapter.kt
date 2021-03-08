package com.zygotecnologia.zygotv.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.SingleGenreBinding
import com.zygotecnologia.zygotv.model.GenreCategory
import com.zygotecnologia.zygotv.model.Show


class GenreAdapter(
    private var context: Context,
    private var onClickListener: GenreAdapter.OnClickListener
) : ListAdapter<GenreCategory, GenreAdapter.ViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreAdapter.ViewHolder {
        return GenreAdapter.ViewHolder(
            SingleGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GenreAdapter.ViewHolder, position: Int) {
        val genreCategory: GenreCategory = getItem(position)

        holder.recyclerView?.setAdapter(ShowAdapter(context, genreCategory.shows!!, ShowAdapter.OnClickListener{
            onClickListener.onClick(it)
        }))
        holder.recyclerView?.setHasFixedSize(true)
        holder.tvHeading?.setText(genreCategory.genreName)

        holder.bind(genreCategory)
    }


    class OnClickListener(val clickListener: (genreCategory: Show) -> Unit) {
        fun onClick(genreCategory: Show) = clickListener(genreCategory)
    }

    class ViewHolder(private var binding: SingleGenreBinding): RecyclerView.ViewHolder(binding.root) {
        var recyclerView: RecyclerView? = null
        var tvHeading: TextView? = null

        init {
            recyclerView = binding.rvShows
        }

        fun bind(
            genreCategory: GenreCategory,
        ){
            binding.viewModel = genreCategory
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<GenreCategory>(){
        override fun areItemsTheSame(
            oldItem: GenreCategory,
            newItem: GenreCategory
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: GenreCategory,
            newItem: GenreCategory
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}