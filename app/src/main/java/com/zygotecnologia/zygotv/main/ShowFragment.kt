package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val SHOW_KEY = "show_id"

@AndroidEntryPoint
class ShowFragment : Fragment() {
    private lateinit var show: Show

    private lateinit var evSeasonsAndEpisodes: ExpandableListView
    private lateinit var tvTitleSeletedShow: TextView
    private lateinit var imgSeletedShow: ImageView

    internal lateinit var callback: FragmentListener

    private val viewModel: DashboardViewModel by viewModels()

    private var listOfSeasons: List<Season>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            show = it.getSerializable(SHOW_KEY) as Show
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        show.id?.let {
            callback.showLoading(true)
            viewModel.loadShowDetails(it)
        }
        setupView()
        setShowMainInformation()
        setupObservers()
    }

    private fun setShowMainInformation() {
        tvTitleSeletedShow.text = show.name
        Glide.with(this)
            .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.img_most_popular_show_custom))
            .into(imgSeletedShow)
    }

    private fun setupObservers() {
        viewModel.mutableListOsSeasonDetail.observe(requireActivity(), {
            listOfSeasons = it
        })

        viewModel.mutableSeasonAndEpisodeMap.observe(requireActivity(), { seasonsAndEpisodes ->
            listOfSeasons?.let {
                val adapter: ExpandableListAdapter =
                    SeasonAdapterBase(requireContext(), it, seasonsAndEpisodes)
                evSeasonsAndEpisodes.setAdapter(adapter)
            }
            callback.showLoading(false)
        })

        viewModel.mutableError.observe(requireActivity(), {
            callback.showError()
        })
    }

    private fun setupView() {
        view?.let {
            evSeasonsAndEpisodes = it.findViewById(R.id.elv_selected_show)
            tvTitleSeletedShow = it.findViewById(R.id.tv_title_selected_show)
            imgSeletedShow = it.findViewById(R.id.img_show_seleted_show)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(show: Show) =
            ShowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SHOW_KEY, show)
                }
            }
    }
}