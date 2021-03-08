package com.zygotecnologia.zygotv.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.model.RequestStatus
import com.zygotecnologia.zygotv.viewmodel.MoviesViewModel
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private var searchJob: Job? = null

    private val viewModel: MoviesViewModel by viewModel()

    protected lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.rvShowList.adapter = GenreAdapter(this, GenreAdapter.OnClickListener{
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("show", it)
            startActivity(intent)
        })

        getGenresAndShows()

        viewModel.statusPopularShows.observe(this, Observer<RequestStatus> { status ->
            when (status) {
                RequestStatus.ERROR -> {
                    Toast.makeText(this, "Erro! Tente novamente mais tarde", Toast.LENGTH_LONG)
                        .show()
                }
                RequestStatus.EMPTY -> {
                    Toast.makeText(this, "Dados não encontrados", Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }


    private fun getGenresAndShows() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchShows()
        }
    }

}