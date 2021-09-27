package com.zygotecnologia.zygotv.series.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.FragmentSerieDetailsBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.repository.SeriesRepository
import com.zygotecnologia.zygotv.series.viewmodel.SeriesViewModel
import com.zygotecnologia.zygotv.series.adapter.SeasonAdapter
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class SerieFragmentDetails(val show: Show) : Fragment() {
    private val mViewModel: SeriesViewModel by viewModel {
        parametersOf(SeriesRepository())
    }
    var _binding: FragmentSerieDetailsBinding? = null
    val binding: FragmentSerieDetailsBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSerieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        mViewModel.getSerieDetails(show.id!!)
        binding.tvTitleSerieDetails.text = show.name
        Glide.with(this)
            .load(show?.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.ivSerieDetails)
    }

    private fun initObserver() {
        mViewModel.dataSeriesDetails.observe(viewLifecycleOwner, {
            mViewModel.loadSeasonDetails(it)
        })
        mViewModel.dataSeasonList.observe(viewLifecycleOwner, {
            binding.rvSeason.layoutManager = LinearLayoutManager(context)
            binding.rvSeason.adapter = SeasonAdapter(it, context!!)
        })
        mViewModel.liveLoading.observe(viewLifecycleOwner, {
            if (!it) {
                binding.pbDetails.visibility = View.GONE
            } else {
                binding.pbDetails.visibility = View.VISIBLE
            }
        })
    }
}