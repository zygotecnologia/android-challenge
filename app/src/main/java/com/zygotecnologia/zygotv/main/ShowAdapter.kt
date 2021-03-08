package com.zygotecnologia.zygotv.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.ShowSingleItemBinding
import com.zygotecnologia.zygotv.model.Show


class ShowAdapter(
    private var context: Context,
    private var shows: ArrayList<Show>,
    private var onClickListener: OnClickListener
) : ListAdapter<Show, ShowAdapter.CustomViewHolder>(DiffCallback) {

    private var inflater: LayoutInflater? = null

    init {
        inflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            ShowSingleItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val show: Show = shows[position]
        holder.itemView.setOnClickListener {
            onClickListener.onClick(show)
        }
        holder.bind(show)
    }

    override fun getItemCount(): Int {
        return shows.size
    }

    class OnClickListener(val clickListener: (show: Show) -> Unit) {
        fun onClick(show: Show) = clickListener(show)
    }

    class CustomViewHolder(private var binding: ShowSingleItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(
            show: Show,
        ){
            binding.viewModel = show
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Show>(){
        override fun areItemsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Show,
            newItem: Show
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }
}