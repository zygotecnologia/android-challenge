package com.zygotecnologia.zygotv.ui.series

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.enum.GenresIdEnum
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.databinding.SeriesFragmentBinding
import com.zygotecnologia.zygotv.ui.MainViewModel
import com.zygotecnologia.zygotv.ui.main.MainAdapter
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : Fragment() {

    private lateinit var binding: SeriesFragmentBinding

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SeriesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        loadShows()
    }

    private fun initObservers(){
        viewModel.genres.observe(viewLifecycleOwner, ::onGetGenres)
        viewModel.shows.observe(viewLifecycleOwner, ::onGetShows)
    }

    private fun onGetGenres(genreResponse: GenreResponse?){
        if(genreResponse != null){
            viewModel.getShows()
        }
    }

    private fun onGetShows(showResponse: ShowResponse?){
        val listShows = showResponse?.results?: emptyList()

        configRecyclerViews(listShows)
        configImageBanner(listShows)
    }

    private fun loadShows(){
        viewModel.getGenres()
    }

    private fun configRecyclerViews(listShows: List<Show>){
        binding.rvShowListComedy.apply {
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(
                GenresIdEnum.COMEDY.generoId) ?: false })
        }

        binding.rvShowListAdventure.apply {
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(
                GenresIdEnum.ACTION_ADVENTURE.generoId) ?: false })
        }

        binding.rvShowListCrime.apply {
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(
                GenresIdEnum.CRIME.generoId) ?: false })
        }

    }
    private fun configImageBanner(listShows: List<Show>){
        var mostPopuplarVotes = 0
        var mostPopularId: Int? = 0

        listShows.forEach { show ->
            show.voteCount?.let {
                if (it > mostPopuplarVotes) {
                    mostPopularId = show.id
                    mostPopuplarVotes = it
                }
            }
        }

        val mostPopularShow = listShows.filter { it.id == mostPopularId }

        listShows.map { Log.d("###", "$mostPopularShow") }

        Glide.with(binding.ivShowPopular)
            .load(mostPopularShow[0].backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.ivShowPopular)

        binding.tvTitleShowPopular.text = mostPopularShow[0].name
    }
}