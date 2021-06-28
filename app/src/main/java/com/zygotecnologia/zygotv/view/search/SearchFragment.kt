package com.zygotecnologia.zygotv.view.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentSearchBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setupToolbar()
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startFetchSearching()
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            isBackEnabled = true
            ivBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        viewModel.results.observe(requireActivity()) { results ->
            binding.hasResults = !results.isNullOrEmpty()
            if(!results.isNullOrEmpty()) {
                binding.rvSearchResults.adapter = SearchAdapter(results)
            }
        }

        viewModel.loading.observe(requireActivity()) {
            binding.isLoading = it
        }

        viewModel.error.observe(requireActivity()) { error ->
            if(error) {
                showErrorMessage()
            }
        }
    }

    private fun showErrorMessage() {
        Snackbar
            .make(binding.root, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) { startFetchSearching() }
            .show()
    }

    private fun startFetchSearching() {
        val searchQuery = arguments?.getString(SEARCH_QUERY_ARG)
        searchQuery?.let {
            lifecycleScope.launch { viewModel.searchTvShow(searchQuery) }
        }
    }

    companion object {
        private const val SEARCH_QUERY_ARG = "searchQuery"
    }
}