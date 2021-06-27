package com.zygotecnologia.zygotv.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.databinding.ShowsFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    companion object {
        fun newInstance() = ShowsFragment()
    }

    private val viewModel: ShowsViewModel by viewModel()

    private lateinit var binding: ShowsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ShowsFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            vm = viewModel
        }
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
                val mySnackbar = Snackbar
                    .make(binding.root, "Aconteceu um erro inesperado. Tente novamente.", Snackbar.LENGTH_LONG)
                mySnackbar.show()
            }
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            loadShows()
        }
    }

    private fun loadShows() {
        lifecycleScope.launch { viewModel.loadShows() }
    }
}