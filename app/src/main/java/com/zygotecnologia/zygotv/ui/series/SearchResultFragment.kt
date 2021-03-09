package com.zygotecnologia.zygotv.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.databinding.FragmentSearchResultBinding
import com.zygotecnologia.zygotv.ui.series.adapter.MainAdapter
import com.zygotecnologia.zygotv.viewmodel.SearchResultViewModel
import com.zygotecnologia.zygotv.viewmodel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchResultFragment : Fragment() {

    private lateinit var searchVm: SearchViewModel
    private val viewModel: SearchResultViewModel by viewModel()

    lateinit var binding: FragmentSearchResultBinding
    val adapter: MainAdapter by lazy {
        MainAdapter(viewModel.shows.value?.results!!,{})
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        searchVm = ViewModelProvider(requireActivity()).get(SearchViewModel::class.java)
        viewModel.shows.observe(viewLifecycleOwner, { shows ->
            binding.rvSearchResult.adapter = adapter
        })
        searchVm.searchText.observe(requireActivity(), { text ->
            adapter.filter.filter(text)
        })

        binding = FragmentSearchResultBinding.inflate(inflater, container, false)

        viewModel.textFilter.observe(requireActivity(), {
            var adapter = binding.rvSearchResult.adapter as MainAdapter
            adapter.filterDataSet(textFilter = it)
        })

        return binding.root

    }

}

