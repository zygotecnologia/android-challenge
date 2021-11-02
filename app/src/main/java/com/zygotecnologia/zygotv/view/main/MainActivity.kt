package com.zygotecnologia.zygotv.view.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.adapter.main.MainGenreAdapter
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.utils.viewVisibility
import com.zygotecnologia.zygotv.view.description.DescriptionActivity
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
                is MainViewState.Loading -> showLoading(true)
            }
        })

        mainViewModel.mostPopularShow.observe(this, {
            setupPopularShow(it)
        })
    }

    private fun setupRecyclerView(shows: List<Show>, genre: List<Genre>) {
        showLoading(false)

        binding.rvShowList.apply {
            adapter = MainGenreAdapter(genre, shows)
            viewVisibility(true)
        }
    }

    private fun setupPopularShow(show: Show) {
        binding.mostPopularShowContainer.apply {
            container.setOnClickListener {
                startActivity(
                    Intent(this@MainActivity, DescriptionActivity::class.java).apply {
                        putExtra(SHOW_ID_KEY, show.id)
                    }
                )
            }

            mostPopularShowTitle.text = show.name

            Glide.with(this@MainActivity)
                .load(show.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
                .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
                .into(popularShowImg)
        }
    }

    private fun showLoading(isVisible: Boolean) {
        binding.loading.viewVisibility(isVisible)
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
    }
}