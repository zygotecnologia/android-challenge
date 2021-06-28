package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.themoviedbapi.TheMovieDbAPI
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private val genresListAPI = TheMovieDbAPI.genresListAPI
    private val popularTvShowsAPI = TheMovieDbAPI.popularTvShowsAPI

    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        launch(Dispatchers.IO) { loadShows() }

    }

    private suspend fun loadShows() {
        val genres =
            genresListAPI
                .get()
                ?.genres
                ?: emptyList()
        val shows =
            popularTvShowsAPI
                .get()
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