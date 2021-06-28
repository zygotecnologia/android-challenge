package com.zygotecnologia.zygotv.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentDetailBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        private const val SHOW_ID_ARG = "showId"
    }

    private val viewModel: DetailViewModel by viewModel()

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupToolbar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        requestLoadShow()
    }

    private fun setupToolbar() {
        binding.apply {
            toolbar.ivBackButton.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun setupObservers() {
        viewModel.show.observe(requireActivity()) {
            binding.show = it

            it.seasons?.let { seasonList ->
                val adapter = SeasonsAdapter(requireContext(), seasonList)
                binding.elvSeasons.setAdapter(adapter)
            }
        }

        viewModel.screenStatus.observe(requireActivity()) {
            binding.screenStatus = it

            if(it.isError) {
                showErrorMessage()
            }
        }
    }

    private fun requestLoadShow() {
        val showId = arguments?.getInt(SHOW_ID_ARG)
        showId?.let {
            lifecycleScope.launch { viewModel.loadShow(it) }
        }
    }

    private fun showErrorMessage() {
        Snackbar
            .make(binding.root, R.string.error_message, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.try_again) { requestLoadShow() }
            .show()
    }
}