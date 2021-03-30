package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.ShowsSearch
import com.zygotecnologia.zygotv.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val SHOW_SEARCH = "show_search"
private const val STRING_SEARCH = "string_search"


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var rvSearchView: RecyclerView

    internal lateinit var callback: FragmentListener

    private lateinit var showsSearch:ShowsSearch
    private lateinit var query:String

    //private lateinit var adapterSearch: ShowSearchedAdapter

    private val viewModel: DashboardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            showsSearch = it.getSerializable(SHOW_SEARCH) as ShowsSearch
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
        setupList()
    }

    private fun setupList() {
        val listOfShowsFiltered = showsSearch.list?.filter { showDetail->
            showDetail.name.toString().toLowerCase().contains(query)
        }
        listOfShowsFiltered?.let {
            rvSearchView.adapter = ShowSearchedAdapter(it)
        }

    }

    private fun setupView() {
        view?.let {
            rvSearchView = it.findViewById(R.id.rv_search_result)
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(query: String?, searchShow:ShowsSearch) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(SHOW_SEARCH, searchShow)
                    putString(STRING_SEARCH, query)
                }
            }
    }
}