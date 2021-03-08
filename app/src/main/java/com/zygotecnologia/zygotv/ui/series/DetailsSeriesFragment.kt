package com.zygotecnologia.zygotv.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.model.seasondetail.SeasonDetail
import com.zygotecnologia.zygotv.databinding.FragmentDetailsSeriesBinding
import com.zygotecnologia.zygotv.ui.series.adapter.DetailAdapter
import com.zygotecnologia.zygotv.ui.series.vm.DetailsSeriesViewModel
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext

class DetailsSeriesFragment : Fragment(), CoroutineScope {

    val show: DetailsSeriesFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailsSeriesBinding

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val viewModel by viewModel<DetailsSeriesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsSeriesBinding.inflate(inflater, container, false)
        binding.show = show.showArgument
        loadShows()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(this)
            .load(show?.showArgument.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(
                RequestOptions().transforms(CenterInside(), RoundedCorners(8))
                    .placeholder(R.drawable.image_placeholder)
            )
            .into(binding.ivFeaturedSeries)
    }

    private  fun loadShows() {
        viewModel.tvId = show.showArgument.id!!
        var seasons : List<SeasonDetail> = mutableListOf()

        viewModel.full.observe(requireActivity(), {
            binding.rvSeason.adapter = DetailAdapter(it)
        })

//        viewModel.seasonDetail.observe(requireActivity(),{ show ->
//            show?.seasons?.forEach {
//                viewModel.episodes.observe(requireActivity(), {
//                    listSeasonDetail += it
//
//                })
//                seasons += show.seasons
//                }
//
//            })




//        val seasons =
//            tmdbApi
//                .fetchShowAsync(show.showArgument.id!!)
//                ?.seasons
//                ?: emptyList()
//

//
//        seasons.forEach {
//            val seasonDetail =
//                tmdbApi
//                    .fetchSeasonDetails(show.showArgument.id!!, it.seasonNumber)
//
//            listSeasonDetail += seasonDetail
//
//        }
//
    }

}