package com.zygotecnologia.zygotv.series.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.series.fragments.SerieFragment
import com.zygotecnologia.zygotv.series.fragments.SerieFragmentDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.android.synthetic.main.show_item.view.*

class SearchAdapter(val listSeries: List<Show>) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(serie: Show) {
            serie.name.let { itemView.tv_show_title.text = it }
            Glide.with(itemView.context)
                .load(serie?.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(itemView.iv_show_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.show_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(listSeries[position])
        holder.itemView.setOnClickListener {
            val activity = it.context as AppCompatActivity
            val transaction: FragmentTransaction =
                activity.supportFragmentManager.beginTransaction().remove(SerieFragment())
            transaction.replace(R.id.fragment_container, SerieFragmentDetails(listSeries[position]))
            transaction.addToBackStack(null)
            transaction.commit()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return listSeries.size
    }
}