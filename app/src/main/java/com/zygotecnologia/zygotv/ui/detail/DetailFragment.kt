package com.zygotecnologia.zygotv.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        binding.apply {
            toolbar.ivBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        requestLoadShow()
    }

    private fun setupObservers() {
        viewModel.show.observe(requireActivity()) {
            binding.show = it

            it.seasons?.let { seasonList ->
                val adapter = SeasonsAdapter(requireContext(), seasonList)
                binding.elvSeasons.apply { setAdapter(adapter) }
            }
        }

        viewModel.loading.observe(requireActivity()) {
            binding.isLoading = it
        }

        viewModel.error.observe(requireActivity()) { error ->
            if(error) {
                val mySnackbar = Snackbar
                    .make(binding.root, "Aconteceu um erro inesperado.", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Tentar Novamente") { requestLoadShow() }
                mySnackbar.show()
            }
        }
    }

    private fun requestLoadShow() {
        val showId = arguments?.getInt(SHOW_ID_ARG)
        showId?.let {
            lifecycleScope.launch { viewModel.loadShow(it) }
        }
    }
}