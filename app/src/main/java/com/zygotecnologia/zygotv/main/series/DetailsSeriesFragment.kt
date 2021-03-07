package com.zygotecnologia.zygotv.main.series

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
import com.zygotecnologia.zygotv.databinding.FragmentDetailsSeriesBinding
import com.zygotecnologia.zygotv.main.series.adapter.DetailAdapter
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.vo.seasondetail.SeasonDetail
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class DetailsSeriesFragment : Fragment(), CoroutineScope {

    val show: DetailsSeriesFragmentArgs by navArgs()
    lateinit var binding: FragmentDetailsSeriesBinding

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val tmdbApi = TmdbClient.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsSeriesBinding.inflate(inflater, container, false)
        binding.show = show.showArgument
        launch(Dispatchers.IO) { loadShows() }
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

    private suspend fun loadShows() {
        val seasons =
            tmdbApi
                .fetchShowAsync(show.showArgument.id!!)
                ?.seasons
                ?: emptyList()

        var listSeasonDetail: List<SeasonDetail?> = mutableListOf()

        seasons.forEach {
            val seasonDetail =
                tmdbApi
                    .fetchSeasonDetails(show.showArgument.id!!, it.seasonNumber)

            listSeasonDetail += seasonDetail

        }

        withContext(Dispatchers.Main) {

            binding.rvSeason.adapter = DetailAdapter(seasons,listSeasonDetail)
////                val action = SpecifyAmountFragmentDirections.confirmationAction(amount)
//
////                val bundle = bundleOf("SHOW" to it)
//                val bundle = bundleOf( "SHOW" to show)
//                findNavController().navigate(R.id.action_homeSeriesFragment_to_detailsSeriesFragment,bundle)
//            })
        }
    }

}