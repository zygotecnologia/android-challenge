package com.zygotecnologia.zygotv.main.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.main.MainApplication
import com.zygotecnologia.zygotv.main.adapters.GenresAdapter
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.model.show.Show
import com.zygotecnologia.zygotv.utils.DialogFactory
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.toHTML
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val showList: RecyclerView by lazy { binding.rvGenreList }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupObservers()
        loadShows()
    }

    private fun loadShows() = viewModel.loadShows()

    private fun setupMostPopular() {
        val show = viewModel.getMostPopularShow()
        binding.mostPopularTitle.text = show?.name
        viewModel.getMostPopularShow()?.backdropPath?.loadImage(binding.root,  binding.banner)
        binding.banner.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.apply {
                putExtra(DetailsActivity.ID_INTENT_EXTRA, show?.id)
                putExtra(DetailsActivity.TITLE_INTENT_EXTRA, show?.name)
                putExtra(DetailsActivity.BANNER_INTENT_EXTRA, show?.backdropPath)
                startActivity(this@apply)
            }
        }
    }

    private fun setupToolbar() {
        setupToolbarText()
        setupToolbarListeners()
    }

    private fun setupToolbarListeners() {
        //TODO
    }

    private fun setupToolbarText() {
        binding.toolbarText.text = resources.getString(R.string.toolbar_text).toHTML()
    }

    private fun setupObservers() {
        errorDialogObserver()
        showListLoadedObserver()
    }

    private fun showListLoadedObserver() {
        viewModel.showList.observe(this, Observer { list ->
            list?.let {
                setListAdapter(it)
                setupMostPopular()
            }
        })
    }

    private fun setListAdapter(showsList: List<Show>) {
        showList.adapter = GenresAdapter(viewModel.filteredGenresList, showsList)
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