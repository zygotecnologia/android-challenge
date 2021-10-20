package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbClient
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val tmdbApi = TmdbClient.getInstance()

    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        launch(Dispatchers.IO) { loadShows() }
    }

    private suspend fun loadShows() {
        val genres =
            tmdbApi
                .fetchGenresAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.genres
                ?: emptyList()
        val shows =
            tmdbApi
                .fetchPopularShowsAsync(TmdbApi.TMDB_API_KEY, "BR")
                ?.results
                ?.map { show ->
                    show.copy(genres = genres.filter { show.genreIds?.contains(it.id) == true })
                }
                ?: emptyList()


        withContext(Dispatchers.Main) {
            showList.adapter = MainAdapter(shows)
        }
    }
}