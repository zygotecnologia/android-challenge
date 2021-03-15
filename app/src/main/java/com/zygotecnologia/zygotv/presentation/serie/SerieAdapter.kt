package com.zygotecnologia.zygotv.presentation.serie

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.databinding.ItemSerieBinding
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SerieAdapter(private val onItemClicked: (ShowResponse) -> Unit) :
    RecyclerView.Adapter<SerieAdapter.SerieAdapterViewHolder>() {
    private lateinit var context: Context
    private val listItems = mutableListOf<ShowResponse>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SerieAdapterViewHolder {
        context = parent.context
        return SerieAdapterViewHolder(
            ItemSerieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addItems(list: List<ShowResponse>) {
        listItems.clear()
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: SerieAdapterViewHolder, position: Int) {
        holder.bind(listItems[position], onItemClicked)
    }

    class SerieAdapterViewHolder(private val binding: ItemSerieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(response: ShowResponse, onItemClicked: (ShowResponse) -> Unit) {

            val urlImage = response.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            binding.imageSerie.load(urlImage)
            binding.nameSerie.text = response.name

            binding.root.setOnClickListener {
                onItemClicked.invoke(response)
            }
        }
    }

}
