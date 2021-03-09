package com.zygotecnologia.zygotv.main

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ShowDetailedItemBinding
import com.zygotecnologia.zygotv.model.Episodes
import com.zygotecnologia.zygotv.model.RequestStatus
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.viewmodel.MoviesViewModel
import kotlinx.coroutines.Job


class ShowDetailedAdapter(
    private val context: Context,
    private val viewModel: MoviesViewModel
) : ListAdapter<Season, ShowDetailedAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShowDetailedAdapter.ViewHolder {
        return ShowDetailedAdapter.ViewHolder(
            ShowDetailedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShowDetailedAdapter.ViewHolder, position: Int) {
        val show: Season = getItem(position)
        //
//        val noOfChildTextViews: Int = holder.itemView.ll_child_items.getChildCount()
//        for (index in 0 until noOfChildTextViews) {
//            val currentTextView =
//                holder.itemView.ll_child_items.getChildAt(index) as TextView
//            currentTextView.visibility = View.VISIBLE
//        }
//
//        val noOfChild: Int = show.episode_count
//        if (noOfChild < noOfChildTextViews) {
//            for (index in noOfChild until noOfChildTextViews) {
//                val currentTextView =
//                    holder.itemView.ll_child_items.getChildAt(index) as TextView
//                currentTextView.visibility = View.GONE
//            }
//        }
//
//        for (textViewIndex in 0 until noOfChild) {
//            val currentTextView =
//                holder.itemView.ll_child_items.getChildAt(textViewIndex) as TextView
//            currentTextView.text = "teste"
//        }

        holder.bind(show, viewModel, context, holder.itemView)
    }

    class ViewHolder(private var binding: ShowDetailedItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var searchJob: Job? = null

        init {
            binding.llChildItems.visibility = View.GONE
        }

        fun bind(
            show: Season,
            viewModel: MoviesViewModel,
            context: Context,
            itemView: View,
        ) {
            binding.viewModel = show
            binding.button.setOnClickListener {
                searchJob?.cancel()
                viewModel.searchEpisode(show.id, show.season_number)
            }

            viewModel.statusEpisode.observe(
                itemView.context as LifecycleOwner,
                Observer<RequestStatus> { status ->
                    when (status) {
                        RequestStatus.ERROR -> {
                            Toast.makeText(
                                context,
                                "Erro! Tente novamente mais tarde",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                        RequestStatus.EMPTY -> {
                            Toast.makeText(context, "Dados não encontrados", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                })

            viewModel.episodes.observe(itemView.context as LifecycleOwner, Observer<Episodes> {
                if (binding.llChildItems.visibility == View.VISIBLE) {
                    binding.button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
                    binding.llChildItems.visibility = View.GONE
                } else {
                    binding.button.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
                    binding.llChildItems.removeAllViews()

                    val layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    layoutParams.setMargins(16, 16, 16, 16)

                    for (indexView in 0 until it.episodes!!.size) {
                        val inflater = LayoutInflater.from(context)
                        val layout = inflater.inflate(
                            R.layout.episode_detailed_item,
                            null,
                            false
                        ) as LinearLayout

                        val episode_name = TextView(context)
                        episode_name.id = indexView
                        episode_name.gravity = Gravity.START
                        episode_name.text = it.episodes.get(indexView).name
                        episode_name.layoutParams = layoutParams
                        layout.addView(episode_name)

                        val episode_overview = TextView(context)
                        episode_overview.id = indexView
                        episode_overview.gravity = Gravity.START
                        episode_overview.text = it.episodes.get(indexView).overview
                        episode_overview.layoutParams = layoutParams
                        layout.addView(episode_overview)

                        val divider = LinearLayout(context)
                        divider.setBackgroundColor(context.resources.getColor(R.color.white))
                        divider.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                        )
                        layout.addView(divider)

                        binding.llChildItems.addView(layout)
                    }
                    binding.llChildItems.visibility = View.VISIBLE
                }
            })
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Season>() {
        override fun areItemsTheSame(
            oldItem: Season,
            newItem: Season
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: Season,
            newItem: Season
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

}