package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint


private const val SHOW_KEY = "Show"

@AndroidEntryPoint
class ShowFragment : Fragment() {
    private lateinit var showDetail: ShowDetails

    private lateinit var evSeasonsAndEpisodes: ExpandableListView
    private lateinit var rvSelectedShow: RecyclerView

    internal lateinit var callback: FragmentListener


    private val viewModel: DashboardViewModel by viewModels()

    private var listOfSeasons: List<Season>? = null
    lateinit var fragmentContext : FragmentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showDetail = it.getSerializable(SHOW_KEY) as ShowDetails
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
        fragmentContext = requireActivity()
        setupView()
        setupObservers()
        showDetail.id?.let {
            viewModel.loadShowDetails(it)

        }
    }

    private fun setupObservers() {
        viewModel.mutableListOsSeasonDetail.observe(fragmentContext, {
            listOfSeasons = it
        })

        viewModel.mutableSeasonAndEpisodeMap.observe(requireActivity() , { seasonsAndEpisodes ->
            listOfSeasons?.let {
                val adapter: ExpandableListAdapter =
                    SeasonAdapterBase(requireContext(), it, seasonsAndEpisodes)
                evSeasonsAndEpisodes.setAdapter(adapter)
            }
        })
    }

    private fun setupView() {
        view?.let {
            evSeasonsAndEpisodes = it.findViewById(R.id.elv_selected_show)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(show: ShowDetails) =
            ShowFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SHOW_KEY, show)
                }
            }
    }
}