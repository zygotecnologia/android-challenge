package com.zygotecnologia.zygotv.view.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.adapter.MainGenreAdapter
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.viewmodel.main.MainViewModel
import com.zygotecnologia.zygotv.viewmodel.main.MainViewModelFactory
import com.zygotecnologia.zygotv.viewmodel.main.MainViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityMainBinding
    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(TmdbClient.getInstance(), coroutineContext)
        ).get(MainViewModel::class.java)

        setupObservers()
    }

    private fun setupObservers() {
        mainViewModel.viewState.observe(this, {
            when (it) {
                is MainViewState.ShowList -> setupRecyclerView(it.showList, it.genreList)
            }
        })

        mainViewModel.mostPopularShow.observe(this, {
            setupPopularShow(it)
        })
    }

    private fun setupRecyclerView(shows: List<Show>, genre: List<Genre>) {
        binding.rvShowList.apply {
            adapter = MainGenreAdapter(genre, shows)
            viewVisibility(true)
        }
    }

    private fun setupPopularShow(show: Show) {
        binding.mostPopularShowContainer.mostPopularShowTitle.text = show.name

        Glide.with(this)
            .load(show.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.mostPopularShowContainer.popularShowImg)
    }

    private fun View.viewVisibility(isVisible: Boolean) {
        this.isVisible = isVisible
    }
}