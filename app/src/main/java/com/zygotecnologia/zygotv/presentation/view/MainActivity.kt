package com.zygotecnologia.zygotv.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.presentation.adapter.MainAdapter
import com.zygotecnologia.zygotv.presentation.gateway.MainViewModel
import com.zygotecnologia.zygotv.presentation.model.ShowResultView
import com.zygotecnologia.zygotv.utils.NetworkUtil
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpObservers()
        setRecycleView()
    }

    private fun setUpObservers() {
        viewModel.popularShows.observe(this) {
            setPopularShows(it)
        }
    }

    private fun setRecycleView() {
        binding.rvShowList.apply {
            layoutManager =  LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    private fun setPopularShows(result: ShowResultView) {
        binding.rvShowList.adapter = MainAdapter(result.mostPopular, result.genre, result.showByGender) {
            val intent = Intent(this, MainDetailActivity::class.java)
            intent.putExtra(MainDetailActivity.PARAM_SHOW, it)
            startActivity(intent)
        }
    }

    private fun loadShows() {
        viewModel.loadShows()
    }

    override fun onResume() {
        super.onResume()
        if (NetworkUtil.hasInternetConnection(this)) {
            loadShows()
        } else {
            Toast.makeText(this, "Precisa de Conexão com a Internet", Toast.LENGTH_LONG).show()
        }
    }
}