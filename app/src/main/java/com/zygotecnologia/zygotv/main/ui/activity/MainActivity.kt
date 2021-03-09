package com.zygotecnologia.zygotv.main.ui.activity

import InputDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.main.adapters.GenresAdapter
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.model.show.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadBackDrop
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadPoster
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_progress_bar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    override val viewModel: MainViewModel by viewModel()

    override val loading: View by lazy { binding.loading.root }
    override val mainContent: View by lazy { binding.mainContent }
    override val toolbarText: TextView by lazy { binding.toolbarText }

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
        setupMostPopularShowName(show)
        setupMostPopularShowBanner()
        setupBannerClickListener(show)
    }

    private fun setupBannerClickListener(show: Show?) {
        binding.banner.setOnClickListener {
            startActivityFromIntent(show)
        }
    }

    private fun startActivityFromIntent(show: Show?) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.apply {
            putExtra(DetailsActivity.ID_INTENT_EXTRA, show?.id)
            putExtra(DetailsActivity.TITLE_INTENT_EXTRA, show?.name)
            putExtra(DetailsActivity.BANNER_INTENT_EXTRA, show?.backdropPath)
            startActivity(this@apply)
        }
    }

    private fun setupMostPopularShowBanner() {
        viewModel.getMostPopularShow()?.backdropPath?.loadBackDrop(binding.root, binding.banner)
    }

    private fun setupMostPopularShowName(show: Show?) {
        binding.mostPopularTitle.text = show?.name
    }

    override fun setupToolbar() {
        super.setupToolbar()
        setupToolbarListeners()
    }

    private fun setupToolbarListeners() {
        binding.toolbarIcon.setOnClickListener {
            InputDialog(
                this,
                getString(R.string.search_show),
                getString(R.string.search_message),
                viewModel.input,
                viewModel.showNames
            ).show()
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        showListLoadedObserver()
        inputSearchObserver()
    }

    private fun inputSearchObserver() {
        viewModel.input.observe(this, Observer { showName ->
            if(!showName.isNullOrEmpty()){
                val show = viewModel.showList.value?.find { it.name == showName }
                startActivityFromIntent(show)
            }
        })
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

}