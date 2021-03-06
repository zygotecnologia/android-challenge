package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.utils.DialogFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private val showList: RecyclerView by lazy { findViewById(R.id.rv_show_list) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupObservers()
        loadShows()
    }

    private fun setupObservers() {
        errorDialogObserver()
        showListLoadedObserver()
    }

    private fun loadShows() = viewModel.loadShows()

    private fun showListLoadedObserver() {
        viewModel.showList.observe(this, Observer { list ->
            list?.let {
                showList.adapter = MainAdapter(it)
            }
        })
    }

    private fun errorDialogObserver() {
        viewModel.errorDialog.observe(this, Observer { error ->
            error?.let {
                DialogFactory.CustomDialog(
                    this,
                    error.title,
                    error.message
                ).show()
            }
        })
    }

}