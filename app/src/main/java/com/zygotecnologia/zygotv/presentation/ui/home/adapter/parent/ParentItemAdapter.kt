package com.zygotecnologia.zygotv.presentation.ui.home.adapter.parent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.presentation.ui.home.item.parent.ParentItem
import com.zygotecnologia.zygotv.presentation.ui.home.item.parent.ParentType
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.ext.loadWithURL
import com.zygotecnologia.zygotv.presentation.ui.home.adapter.child.ChildItemAdapter
import com.zygotecnologia.zygotv.presentation.ui.home.adapter.child.ChildItemListener
import com.zygotecnologia.zygotv.utils.Constants.childType
import com.zygotecnologia.zygotv.utils.Constants.favoriteType
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class ParentItemAdapter(private val childItemListener: ChildItemListener) : RecyclerView.Adapter<ParentItemAdapter.BaseViewHolder<*>>(){
    var data = listOf<ParentItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private val viewPoll : RecyclerView.RecycledViewPool = RecyclerView.RecycledViewPool()

    override fun getItemViewType(position: Int): Int {
        val comparable = data[position]
        return when (comparable.type){
            ParentType.Child -> childType
            ParentType.Favorite -> favoriteType
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when(viewType){
            favoriteType -> {
                val view = layoutInflater
                    .inflate(R.layout.favorite_item, parent, false)
                FavoriteItemViewHolder(view)
            }
            else -> {
                val view = layoutInflater
                    .inflate(R.layout.parent_item, parent, false)
                ChildItemViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = data.size


   inner class ChildItemViewHolder(itemView: View) : BaseViewHolder<ParentItem>(itemView){
        private val title : TextView = itemView.findViewById(R.id.parent_item_title)
        private val childRecycler : RecyclerView = itemView.findViewById(R.id.child_recyclerview)
        override fun bind(item: ParentItem) {
            this.title.text = item.title
            val linearLayoutManager =
                LinearLayoutManager(this.childRecycler.context, LinearLayoutManager.HORIZONTAL, false).apply {
                    initialPrefetchItemCount = item.childItems.size
                }
            this.childRecycler.layoutManager = linearLayoutManager
            this.childRecycler.adapter = ChildItemAdapter(childItemListener).apply { data = item.childItems }
            this.childRecycler.setRecycledViewPool(viewPoll)
        }
    }

    inner class FavoriteItemViewHolder(itemView: View) : BaseViewHolder<ParentItem>(itemView){
        private val title: TextView = itemView.findViewById(R.id.favorite_item_title)
        private val image : ImageView = itemView.findViewById(R.id.img_favorite_item)
        private val card : ConstraintLayout = itemView.findViewById(R.id.cl_top_votes_series)
        override fun bind(item: ParentItem) {
            this.title.text = item.title
            this.image.loadWithURL(
                ImageUrlBuilder.buildPosterUrl(item.childItems[0].url),
                RequestOptions()
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_loading)
            )
            this.card.setOnClickListener {
                childItemListener.onClick(item.childItems[0])
            }
        }
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: ParentItem)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val element = data[position]
        holder.bind(element)
    }
}