package com.zygotecnologia.zygotv.presentation.ui.home.adapter.child

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.presentation.ui.home.item.child.ChildItem
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.ext.loadWithURL
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ChildItemAdapter(private val listener: ChildItemListener) :
    RecyclerView.Adapter<ChildItemAdapter.ViewHolder>() {
    var data = listOf<ChildItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.child_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.title.text = item.title
        holder.card.setOnClickListener {
            listener.onClick(item)
        }

        holder.image.loadWithURL(
            ImageUrlBuilder.buildPosterUrl(item.url),
            RequestOptions()
                .placeholder(R.drawable.ic_loading)
                .error(R.drawable.ic_loading)
        )
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.child_item_title)
        val card: CardView = itemView.findViewById(R.id.cv_child_item)
        val image: ImageView = itemView.findViewById(R.id.img_child_item)
    }

    override fun getItemCount(): Int = data.size
}
