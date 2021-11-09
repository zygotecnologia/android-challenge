package com.zygotecnologia.zygotv.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.R.id.group_title
import com.zygotecnologia.zygotv.R.id.show_list
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowByGenre


class HomeAdapter(
    private val clickListener: (data: Show) -> Unit
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    private var shows = listOf<ShowByGenre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.show_group, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shows[position])

        val showAdapter = ShowAdapter(shows[position].shows, clickListener)
        val recycler: RecyclerView = holder.itemView.findViewById(show_list)

        recycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = showAdapter
            isNestedScrollingEnabled = false
            hasFixedSize()
        }
    }

    override fun getItemCount() = shows.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(genre: ShowByGenre) {
            val textView: TextView = itemView.findViewById(group_title)
            textView.text = genre.genre
        }
    }

    fun updateShowList(showList: List<ShowByGenre>) {
        shows = showList - showList[0]
        notifyDataSetChanged()

    }
}