package com.zygotecnologia.zygotv.adapter.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.adapter.main.MainShowAdapter
import com.zygotecnologia.zygotv.databinding.RowShowItemBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.setBackEndImage
import com.zygotecnologia.zygotv.view.description.DescriptionActivity

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
        RecyclerView.ViewHolder(searchShowView.root), View.OnClickListener {

        private var showId: Int = 0

        fun bind(show: Show) {
            showId = show.id ?: 0
            searchShowView.showItem.setOnClickListener {
                onClick(it)
            }
            searchShowView.tvShowTitle.text = show.name
            searchShowView.ivShowPoster.setBackEndImage(
                itemView.context,
                show.posterPath ?: ""
            )
        }

        override fun onClick(v: View?) {
            searchShowView.root.context.apply {
                startActivity(Intent(this, DescriptionActivity::class.java).apply {
                    putExtra(SHOW_ID_KEY, showId)
                })
            }
        }
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
    }
}