package com.zygotecnologia.zygotv.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.zygotecnologia.zygotv.databinding.SearchFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    companion object {
        private const val SEARCH_QUERY_ARG = "searchQuery"
    }

    private val viewModel: SearchViewModel by viewModel()

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.toolbar.apply {
            isBackEnabled = true
            ivBackButton.setOnClickListener {
                root.findNavController().navigateUp()
            }
        }
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchQuery = arguments?.getString(SEARCH_QUERY_ARG)
        searchQuery?.let { viewModel.searchTvShow(searchQuery) }
    }

    private fun setupObservers() {
        viewModel.results.observe(requireActivity()) {
            if(it != null) {
                binding.rvSearchResults.adapter = SearchAdapter(it)
            } else {
                // TODO no results
            }
        }
    }
}