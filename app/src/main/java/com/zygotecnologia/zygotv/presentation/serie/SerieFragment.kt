package com.zygotecnologia.zygotv.presentation.serie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.gone
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.common.showSnackBar
import com.zygotecnologia.zygotv.common.uistate.State
import com.zygotecnologia.zygotv.common.visible
import com.zygotecnologia.zygotv.databinding.FragmentSeriesBinding
import com.zygotecnologia.zygotv.service.remote.data.serie.ShowResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.buildBackdropUrl
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class SerieFragment : Fragment(R.layout.fragment_series) {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private val serieComedyAdapter = SerieAdapter(::onComedyClicked)

    private val serieRomanceAdapter = SerieAdapter(::onRomanceClicked)

    private val viewModel by viewModel<SeriesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadComponents()

        loadActions()




    }

    private fun loadActions() {
        viewModel.highlightsLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.shimmerzinho.shimmerDest.startShimmer()
                }
                is State.Success -> {
                    binding.shimmerzinho.shimmerDest.stopShimmer()
                    binding.shimmerzinho.shimmerDest.gone()
                    binding.textoDest.visible()
                    binding.imageDest.visible()
                    val backdrop = state.data.backdropPath?.let { buildBackdropUrl(it) }
                    binding.textoDest.text = state.data.name
                    binding.imageDest.load(backdrop)
                }
                is State.Error -> {
                    binding.shimmerzinho.shimmerDest.stopShimmer()
                    binding.shimmerzinho.shimmerDest.gone()
                    binding.root.showSnackBar(
                        binding.root,
                        getString(R.string.get_series_error),
                        Snackbar.LENGTH_LONG
                    )
                }
            }


        }

        viewModel.comedyLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.shimmerComedia.shimmerLayout.startShimmer()

                }
                is State.Success -> {
                    binding.shimmerComedia.shimmerLayout.stopShimmer()
                    binding.shimmerComedia.shimmerLayout.gone()

                    binding.recyclerComedia.visible()
                    binding.titleRecyclerComedia.visible()
                    serieComedyAdapter.addItems(state.data)
                }
                is State.Error -> {
                    binding.shimmerComedia.shimmerLayout.stopShimmer()
                    binding.shimmerComedia.shimmerLayout.gone()
                    binding.root.showSnackBar(
                        binding.root,
                        getString(R.string.get_series_error),
                        Snackbar.LENGTH_LONG
                    )
                }
            }
        }

        viewModel.romanceLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Loading -> {
                    binding.shimmerRomance.shimmerLayout.startShimmer()
                }
                is State.Success -> {
                    binding.shimmerRomance.shimmerLayout.stopShimmer()
                    binding.shimmerRomance.shimmerLayout.gone()
                    binding.recyclerRomance.visible()
                    binding.titleRecyclerRomance.visible()
                    serieRomanceAdapter.addItems(state.data)
                }
                is State.Error -> {
                    binding.shimmerRomance.shimmerLayout.stopShimmer()
                    binding.shimmerRomance.shimmerLayout.gone()
                    binding.root.showSnackBar(
                        binding.root,
                        getString(R.string.get_series_error),
                        Snackbar.LENGTH_LONG
                    )
                }
            }
        }

        viewModel.highlightsClickLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.Success -> {
                    navigateSeasons(state.data)
                }
                is State.Error -> {
                    binding.root.showSnackBar(
                        binding.root,
                        getString(R.string.get_series_error),
                        Snackbar.LENGTH_LONG
                    )
                }
            }
        }
    }

    private fun loadComponents() {
        binding.recyclerComedia.apply {
            adapter = serieComedyAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.imageDest.setOnClickListener {
            viewModel.clickItemHighlights()
        }

        binding.recyclerRomance.apply {
            adapter = serieRomanceAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }

    private fun onComedyClicked(response: ShowResponse) = navigateSeasons(response)

    private fun onRomanceClicked(response: ShowResponse) = navigateSeasons(response)

    private fun navigateSeasons(response: ShowResponse) {
        findNavController().navigate(
            SerieFragmentDirections.actionSeriesFragmentToSerieDetailFragment(
                response
            )
        )
    }
}