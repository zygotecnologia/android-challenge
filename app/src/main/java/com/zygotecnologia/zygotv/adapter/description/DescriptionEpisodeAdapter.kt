package com.zygotecnologia.zygotv.adapter.description

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.RowEpisodeItemBinding
import com.zygotecnologia.zygotv.model.Episodes

class DescriptionEpisodeAdapter(
    private val episodeList: List<Episodes>
) : RecyclerView.Adapter<DescriptionEpisodeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val episodeBind = RowEpisodeItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(episodeBind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    override fun getItemCount() = episodeList.size

    class ViewHolder(private val episodeView: RowEpisodeItemBinding) :
        RecyclerView.ViewHolder(episodeView.root) {

        fun bind(episode: Episodes) {
            episodeView.episodeName.text = episode.name
            episodeView.episodeOverview.text = episode.description
        }
    }
}