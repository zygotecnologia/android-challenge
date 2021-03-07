package com.zygotecnologia.zygotv.main.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.custom.ZygoTextfField
import com.zygotecnologia.zygotv.databinding.FragmentHomeSeriesBinding
import com.zygotecnologia.zygotv.main.MainAdapter
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomeSeriesFragment : Fragment(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val tmdbApi = TmdbClient.getInstance()

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
        launch(Dispatchers.IO) { loadShows() }
    }


    private suspend fun loadShows() {
        val genres =
            tmdbApi
                .fetchGenresAsync("BR")
                ?.genres
                ?: emptyList()
        val shows =
            tmdbApi
                .fetchPopularShowsAsync("BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()

        withContext(Dispatchers.Main) {
            binding.rvComedyCategory.adapter = MainAdapter(shows, itemClickListener = { show ->
                val action =
                    HomeSeriesFragmentDirections.actionHomeSeriesFragmentToDetailsSeriesFragment(
                        show
                    )
                findNavController().navigate(action)
            })

            binding.rvRomanceCategory.adapter = MainAdapter(shows, itemClickListener = { show ->
                val action =
                    HomeSeriesFragmentDirections.actionHomeSeriesFragmentToDetailsSeriesFragment(
                        show
                    )
                findNavController().navigate(action)
            })

            binding.labelFeaturedSeriesName.text = shows[0].name
            Glide.with(this@HomeSeriesFragment)
                .load(shows[0].posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
                .apply(
                    RequestOptions().transforms(CenterInside(), RoundedCorners(8))
                        .placeholder(R.drawable.image_placeholder)
                )
                .into(binding.ivFeaturedSeries)

        }
    }
}