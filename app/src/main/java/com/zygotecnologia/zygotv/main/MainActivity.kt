package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.model.Genre
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.viewmodel.MainViewModel
import com.zygotecnologia.zygotv.viewmodel.MainViewModelFactory
import com.zygotecnologia.zygotv.viewmodel.MainViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    private lateinit var mainViewModel: MainViewModel
    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    }

    private fun setupRecyclerView(shows: List<Show>, genre: List<Genre>) {
        showList.apply {
            adapter = MainGenreAdapter(genre, shows)
            viewVisibility(true)
        }
    }

    private fun View.viewVisibility(isVisible: Boolean) {
        this.isVisible = isVisible
    }
}