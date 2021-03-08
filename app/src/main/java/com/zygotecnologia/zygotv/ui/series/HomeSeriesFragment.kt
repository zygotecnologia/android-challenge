package com.zygotecnologia.zygotv.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentHomeSeriesBinding
import com.zygotecnologia.zygotv.ui.MainAdapter
import com.zygotecnologia.zygotv.ui.series.vm.HomeSeriesViewModel
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeSeriesFragment : Fragment() {

    private val viewModel by viewModel<HomeSeriesViewModel>()

    lateinit var binding: FragmentHomeSeriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadShows()
    }


    private fun loadShows() {

        viewModel.shows.observe(requireActivity(), { shows ->
            binding.rvComedyCategory.adapter = MainAdapter(shows?.results!!, itemClickListener = { show ->
                val action =
                    HomeSeriesFragmentDirections.actionHomeSeriesFragmentToDetailsSeriesFragment(
                        show
                    )
                findNavController().navigate(action)
            })

            binding.rvRomanceCategory.adapter = MainAdapter(shows?.results!!, itemClickListener = { show ->
                val action =
                    HomeSeriesFragmentDirections.actionHomeSeriesFragmentToDetailsSeriesFragment(
                        show
                    )
                findNavController().navigate(action)
            })

            binding.labelFeaturedSeriesName.text = shows.results[0].name
            Glide.with(this@HomeSeriesFragment)
                .load(shows.results[0].posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(
                    RequestOptions().transforms(CenterInside(), RoundedCorners(8))
                        .placeholder(R.drawable.image_placeholder)
                )
                .into(binding.ivFeaturedSeries)

        })





        }

}