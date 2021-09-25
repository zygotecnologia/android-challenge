package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.data.enum.GenresIdEnum
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initObservers()

        loadShows()
    }

    private fun initObservers(){
        viewModel.genres.observe(this, ::onGetGenres)
        viewModel.shows.observe(this, ::onGetShows)
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
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(GenresIdEnum.COMEDY.generoId) ?: false })
        }

        binding.rvShowListAdventure.apply {
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(GenresIdEnum.ACTION_ADVENTURE.generoId) ?: false })
        }

        binding.rvShowListCrime.apply {
            this.adapter = MainAdapter(listShows.filter { show -> show.genreIds?.contains(GenresIdEnum.CRIME.generoId) ?: false })
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