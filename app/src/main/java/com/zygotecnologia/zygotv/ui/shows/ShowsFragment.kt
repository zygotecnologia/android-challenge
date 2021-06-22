package com.zygotecnologia.zygotv.ui.shows

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.zygotecnologia.zygotv.databinding.ShowsFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShowsFragment : Fragment() {

    companion object {
        fun newInstance() = ShowsFragment()
    }

    private val viewModel: ShowsViewModel by viewModel()

    private var _binding: ShowsFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShowsFragmentBinding.inflate(inflater, container, false)
        lifecycleScope.launch {
            viewModel.loadShows()
            viewModel.loadMostPopularShow()
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        viewModel.showList.observe(requireActivity()) { shows ->
            binding.rvShowList.adapter = ShowsAdapter(shows)
        }
        viewModel.mostPopularShow.observe(requireActivity()) {
            binding.mostPopular = it
        }
    }
}