package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.DialogFactory
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.toHTML
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val showList: RecyclerView by lazy { binding.rvShowList }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupObservers()
        loadShows()
    }

    private fun setupMostPopular() {
        binding.mostPopularTitle.text = viewModel.getMostPopularShow()?.name
        viewModel.getMostPopularShow()?.backdropPath?.loadImage(binding.root,  binding.banner)
    }

    private fun setupToolbar() {
        binding.toolbarText.text = "<html>Zygo<font color='red'>TV</font></html>".toHTML()
    }

    private fun setupObservers() {
        errorDialogObserver()
        showListLoadedObserver()
    }

    private fun loadShows() = viewModel.loadShows()

    private fun showListLoadedObserver() {
        viewModel.showList.observe(this, Observer { list ->
            list?.let {
                setListAdapter(it)
                setupMostPopular()
            }
        })
    }

    private fun setListAdapter(it: List<Show>) {
        showList.adapter = MainAdapter(it)
    }

    private fun errorDialogObserver() {
        viewModel.errorDialog.observe(this, Observer { error ->
            error?.let {
                DialogFactory.showAlertDialog(
                    this,
                    error.title,
                    error.message
                )
            }
        })
    }

}