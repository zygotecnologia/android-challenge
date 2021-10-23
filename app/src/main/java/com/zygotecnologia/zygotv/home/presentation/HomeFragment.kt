package com.zygotecnologia.zygotv.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import com.zygotecnologia.zygotv.tmdb.domain.show.Show
import com.zygotecnologia.zygotv.tmdb.presentation.genres.GenresAdapter
import com.zygotecnologia.zygotv.tmdb.presentation.HighlightedShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureList()
        observeNetworkError()
    }

    private fun configureList() {
        val highlightedShowAdapter = createHighlightedShowAdapter()
        val genresAdapter = createGenresAdapter()

        binding.homeRecycler.adapter = ConcatAdapter(
            highlightedShowAdapter,
            genresAdapter
        )
    }

    private fun createHighlightedShowAdapter() = HighlightedShowAdapter().also { showAdapter ->
        viewModel.mostPopularShow.observe(viewLifecycleOwner) {
            showAdapter.updateHighlightedShow(it)
        }
    }

    private fun createGenresAdapter(): GenresAdapter {
        val adapter = GenresAdapter(
            onShowClicked = { openDetails(it) }
        )

        viewModel.showsByGenre.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return adapter
    }

    private fun openDetails(show: Show) {
        findNavController().navigate(
            HomeFragmentDirections.openDetails(show.id)
        )
    }

    private fun observeNetworkError() {
        val errorSnackBar =
            Snackbar.make(binding.root, R.string.network_error, Snackbar.LENGTH_INDEFINITE)

        viewModel.showNetworkError.observe(viewLifecycleOwner) { hasError ->
            if (hasError) errorSnackBar.show()
            else errorSnackBar.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}