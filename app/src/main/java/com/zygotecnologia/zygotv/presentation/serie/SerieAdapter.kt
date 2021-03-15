package com.zygotecnologia.zygotv.presentation.serie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class SerieAdapter(private val onItemClicked: (ShowResponse) -> Unit) :
    RecyclerView.Adapter<SerieAdapter.ComedyAdapterViewHolder>() {
    private lateinit var context: Context
    private val listItems = mutableListOf<ShowResponse>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComedyAdapterViewHolder {
        context = parent.context
        return ComedyAdapterViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.item_serie,
                parent,
                false
            )
        )
    }

    fun addItems(list: List<ShowResponse>) {
        listItems.clear()
        listItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listItems.size

    override fun onBindViewHolder(holder: ComedyAdapterViewHolder, position: Int) {
        holder.bind(listItems[position], onItemClicked)

    }

    class ComedyAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(response: ShowResponse, onItemClicked: (ShowResponse) -> Unit) {
            val image = itemView.findViewById<ImageView>(R.id.image_serie)
            val serieName = itemView.findViewById<TextView>(R.id.name_serie)
            val urlImage = response.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) }
            image.load(urlImage)
            serieName.text = response.name

            itemView.setOnClickListener {
                onItemClicked.invoke(response)
            }
        }
    }

}
