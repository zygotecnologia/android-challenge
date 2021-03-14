package com.zygotecnologia.zygotv.presentation.series

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.common.load
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.common.gone
import com.zygotecnologia.zygotv.common.showAnimate
import com.zygotecnologia.zygotv.common.visible
import com.zygotecnologia.zygotv.databinding.FragmentSeriesBinding
import com.zygotecnologia.zygotv.service.remote.data.ShowResponse
import com.zygotecnologia.zygotv.uistate.State
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.buildBackdropUrl
import org.koin.android.viewmodel.ext.android.viewModel


class SeriesFragment : Fragment(R.layout.fragment_series) {

    private var _binding: FragmentSeriesBinding? = null
    private val binding get() = _binding!!

    private val serieComedyAdapter = ComedyAdapter(::onComedyClicked)

    private val serieRomanceAdapter = ComedyAdapter(::onRomanceClicked)


    private fun onComedyClicked(response: ShowResponse) {

    }


    private fun onRomanceClicked(response: ShowResponse) {

    }


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


        binding.recyclerComedia.apply {
            adapter = serieComedyAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        binding.recyclerRomance.apply {
            adapter = serieRomanceAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }

        viewModel.firstSerieLiveData.observe(viewLifecycleOwner){ state ->
            when(state){
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

                }
            }


        }

        viewModel.comediaLiveData.observe(viewLifecycleOwner){ state->
            when(state){
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

                }
            }
        }



        viewModel.romanceLiveData.observe(viewLifecycleOwner){ state->
            when(state){
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

                }
            }
        }


    }
}