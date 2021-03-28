package com.zygotecnologia.zygotv.views.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.databinding.ListSectionItemBinding
import com.zygotecnologia.zygotv.model.entity.Section

class SectionAdapter(private val sections: List<Section>) : RecyclerView.Adapter<SectionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ListSectionItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(view, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sections[position])
    }

    override fun getItemCount() = sections.size

    class ViewHolder(
        private val binding: ListSectionItemBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(section: Section) {
            binding.section = section
            binding.executePendingBindings()

            binding.sectionItemList.layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            binding.sectionItemList.adapter = ShowItemAdapter(section)
        }
    }
}