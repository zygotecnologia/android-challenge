package com.zygotecnologia.zygotv.view.main

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.zygotecnologia.zygotv.adapter.main.MainGenreAdapter
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.utils.setBackEndImage
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

        mainViewModel.isConnected {
            val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            activeNetwork?.isConnectedOrConnecting == true
        }
    }

    private fun setupObservers() {
        mainViewModel.viewState.observe(this, {
            when (it) {
                is MainViewState.ShowList -> setupRecyclerView(it.showList, it.genreList)
                is MainViewState.Loading -> showLoading(true)
                is MainViewState.ConnectionStatus -> {
                    if (it.isOnline) {
                        mainViewModel.fetchMovies()
                    } else {
                        binding.noConnectionContent.root.viewVisibility(true)
                    }
                }
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
            popularShowImg.setBackEndImage(
                this@MainActivity,
                ImageUrlBuilder.buildBackdropUrl(show.backdropPath ?: "")
            )
        }
    }

    private fun showLoading(isVisible: Boolean) {
        binding.loading.viewVisibility(isVisible)
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
    }
}