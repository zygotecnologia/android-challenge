package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.network.TmdbApi
import com.zygotecnologia.zygotv.network.TmdbClient
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.loadShows().observe(this, {
            showList.adapter = MainAdapter(it)
        })
    }


}