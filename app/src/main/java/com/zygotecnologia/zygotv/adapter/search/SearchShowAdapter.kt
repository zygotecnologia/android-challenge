package com.zygotecnologia.zygotv.adapter.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.RowShowItemBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.setBackEndImage

class SearchShowAdapter(
    private val showList: MutableList<Show>
) : RecyclerView.Adapter<SearchShowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val searchedShow = RowShowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(searchedShow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(showList[position])
    }

    override fun getItemCount() = showList.size

    fun updateShowList(searchedShowList: List<Show>) {
        showList.clear()
        showList.addAll(searchedShowList)
        notifyDataSetChanged()
    }

    class ViewHolder(private val searchShowView: RowShowItemBinding) :
        RecyclerView.ViewHolder(searchShowView.root) {

        fun bind(show: Show) {
            searchShowView.tvShowTitle.text = show.name
            searchShowView.ivShowPoster.setBackEndImage(
                itemView.context,
                show.posterPath ?: ""
            )
        }
    }
}