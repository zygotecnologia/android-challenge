package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.model.ShowDetails
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val STRING_SEARCH = "string_search"


@AndroidEntryPoint
class SearchFragment : Fragment(), OnCLickShowListener {
    private lateinit var rvSearchView: RecyclerView

    internal lateinit var callback: FragmentListener

    private lateinit var query:String

    private lateinit var listOfShowsSet: List<Show>

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            query = it.getString(STRING_SEARCH)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setObservers()
        callback.showLoading(true)
        viewModel.loadListOfShows()
    }

    private fun setObservers() {
        viewModel.mutableListOfShowDetails.observe(requireActivity(), { showList->
            setupList(showList)
            callback.showLoading(false)
        })

        viewModel.mutableError.observe(requireActivity(), {
            callback.showError()
        })
    }

    private fun setupList(listOfShowDetailsDetails: List<Show>?) {
        val listOfShowsFiltered = listOfShowDetailsDetails?.filter { showDetail->
            showDetail.name.toString().toLowerCase().contains(query)
        }
        listOfShowsFiltered?.let {
            listOfShowsSet = it
            rvSearchView.adapter = ShowSearchedAdapter(it, this)
        }

    }

    private fun setupView() {
        view?.let {
            rvSearchView = it.findViewById(R.id.rv_search_result)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(query: String?) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(STRING_SEARCH, query)
                }
            }
    }

    override fun onShowClick(position: Int) {
        val clickedShowDetails = listOfShowsSet[position]
        callback.nextFragment(ShowFragment.newInstance(clickedShowDetails))
    }
}