package com.zygotecnologia.zygotv.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.zygotecnologia.zygotv.databinding.DetailFragmentBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        private const val SHOW_ID_ARG = "showId"
    }

    private val viewModel: DetailViewModel by viewModel()

    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            toolbar.ivBackButton.setOnClickListener {
                root.findNavController().navigateUp()
            }
        }
        val showId = arguments?.getInt(SHOW_ID_ARG)
        showId?.let {
            lifecycleScope.launch { viewModel.loadShow(it) }
        }
        return binding.root
    }

    private fun setupObservers() {
        viewModel.show.observe(requireActivity()) {
            binding.show = it

            it.seasons?.let { seasonList ->
                val adapter = SeasonsAdapter(requireContext(), seasonList)
                binding.elvSeasons.apply {
                    setAdapter(adapter)
                    setOnGroupExpandListener {

                    }
                    setOnGroupCollapseListener {

                    }
                }
            }
        }
    }
}