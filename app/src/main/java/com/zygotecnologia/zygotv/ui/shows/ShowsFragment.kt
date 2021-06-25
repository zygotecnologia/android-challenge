package com.zygotecnologia.zygotv.ui.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.databinding.ShowsFragmentBinding
import com.zygotecnologia.zygotv.ui.home.HomeFragmentDirections
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    companion object {
        fun newInstance() = ShowsFragment()
    }

    private val viewModel: ShowsViewModel by viewModel()

    private var _binding: ShowsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShowsFragmentBinding.inflate(inflater, container, false)
        binding.vm = viewModel
        lifecycleScope.launch {
            viewModel.loadShows()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.genreList.observe(requireActivity()) { genres ->
            binding.rvGenresList.adapter = GenresAdapter(genres)
        }

        viewModel.selectedShow.observe(requireActivity()) {
            val direction = HomeFragmentDirections.actionHomeFragmentToShowDetailFragment(it)
            binding.root.findNavController().navigate(direction)
        }

        viewModel.mostPopularShow.observe(requireActivity()) {
            binding.mostPopular = it
        }

        viewModel.loading.observe(requireActivity()) {
            binding.isLoading = it
        }

        viewModel.error.observe(requireActivity()) {
            val mySnackbar = Snackbar
                .make(binding.root, "Aconteceu um erro inesperado.", Snackbar.LENGTH_INDEFINITE)
                .setAction("Tentar Novamente") {
                    lifecycleScope.launch { viewModel.loadShows() }
                }
            mySnackbar.show()
        }
    }
}