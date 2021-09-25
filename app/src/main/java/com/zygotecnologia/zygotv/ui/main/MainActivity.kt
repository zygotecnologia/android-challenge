package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.data.model.GenreResponse
import com.zygotecnologia.zygotv.data.model.Show
import com.zygotecnologia.zygotv.data.model.ShowResponse
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
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
        configRecyclerView(showResponse?.results?: emptyList())
    }

    private fun loadShows(){
        viewModel.getGenres()
    }

    private fun configRecyclerView(listShows: List<Show>){
        binding.rvShowList.apply {
            this.adapter = MainAdapter(listShows)
        }
    }
}