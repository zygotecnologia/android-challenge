package com.zygotecnologia.zygotv.view.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentShowsBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    private val viewModel: ShowsViewModel by viewModel()

    private lateinit var binding: FragmentShowsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShowsBinding.inflate(inflater, container, false)
        binding.apply { vm = viewModel }
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadShows()
    }

    private fun setupObservers() {
        viewModel.showsContent.observe(requireActivity()) { content ->
            binding.rvGenresList.adapter = GenresAdapter(content.first, content.second)
        }

        viewModel.loading.observe(requireActivity()) {
            binding.isLoading = it
        }

        viewModel.error.observe(requireActivity()) { error ->
            if(error) {
                showErrorMessage()
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadShows()
        }
    }

    private fun showErrorMessage() {
        Snackbar
            .make(binding.root, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) { loadShows() }
            .show()
    }

    private fun loadShows() {
        lifecycleScope.launch { viewModel.loadShows() }
    }

    companion object {
        fun newInstance() = ShowsFragment()
    }
}