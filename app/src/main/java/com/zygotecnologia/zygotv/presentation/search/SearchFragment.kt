package com.zygotecnologia.zygotv.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.databinding.SearchFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    companion object {
        private const val SEARCH_QUERY_ARG = "searchQuery"
    }

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.toolbar.apply {
            isBackEnabled = true
            ivBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startFetchSearching()
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
                val mySnackbar = Snackbar
                    .make(binding.root, "Aconteceu um erro inesperado.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Tentar Novamente") { startFetchSearching() }
                mySnackbar.show()
            }
        }
    }

    private fun startFetchSearching() {
        val searchQuery = arguments?.getString(SEARCH_QUERY_ARG)
        searchQuery?.let { lifecycleScope.launch { viewModel.searchTvShow(searchQuery) } }
    }
}