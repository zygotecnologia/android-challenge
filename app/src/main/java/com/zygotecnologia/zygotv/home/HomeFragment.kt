package com.zygotecnologia.zygotv.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import com.zygotecnologia.zygotv.databinding.HomeFragmentBinding
import com.zygotecnologia.zygotv.tmdb.presentation.GenresAdapter
import com.zygotecnologia.zygotv.tmdb.presentation.HighlightedShowAdapter
import com.zygotecnologia.zygotv.tmdb.presentation.ShowsAdapter
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

    private fun createGenresAdapter() = GenresAdapter().also { genreAdapter ->
        viewModel.showsByGenre.observe(viewLifecycleOwner) {
            genreAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}