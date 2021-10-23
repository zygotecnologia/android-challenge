package com.zygotecnologia.zygotv.details.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.DetailsFragmentBinding
import com.zygotecnologia.zygotv.tmdb.presentation.HighlightedShowAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class DetailsFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    private val viewModel: DetailsViewModel by viewModel {
        parametersOf(args.showId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureList()
        observeNetworkError()
    }

    private fun configureList() {
        val adapter = HighlightedShowAdapter()

        binding.detailsRecycler.adapter = adapter

        viewModel.show.observe(viewLifecycleOwner) {
            adapter.updateHighlightedShow(it)
        }
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