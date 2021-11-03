package com.zygotecnologia.zygotv.view.description

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zygotecnologia.zygotv.adapter.description.DescriptionSeasonAdapter
import com.zygotecnologia.zygotv.databinding.ActivityShowDescriptionBinding
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
import com.zygotecnologia.zygotv.utils.setBackEndImage
import com.zygotecnologia.zygotv.utils.viewVisibility
import com.zygotecnologia.zygotv.view.search.SearchActivity
import com.zygotecnologia.zygotv.viewmodel.description.DescriptionViewModel
import com.zygotecnologia.zygotv.viewmodel.description.DescriptionViewModelFactory
import com.zygotecnologia.zygotv.viewmodel.description.DescriptionViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class DescriptionActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var binding: ActivityShowDescriptionBinding
    private lateinit var viewModel: DescriptionViewModel

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + Dispatchers.IO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            DescriptionViewModelFactory(
                TmdbClient.getInstance(), coroutineContext
            )
        ).get(DescriptionViewModel::class.java)

        setupObservers()
        setupListener()

        viewModel.fetchShowDescription(intent.getIntExtra(SHOW_ID_KEY, DEFAULT_VALUE_SHOW_ID))
    }

    private fun setupListener() {
        binding.searchShow.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    private fun setupObservers() {
        viewModel.viewState.observe(this, {
            when (it) {
                is DescriptionViewState.ShowAndSeasonsDescriptions -> setupShowInfo(it.show)
                is DescriptionViewState.Loading -> showLoading(true)
            }
        })
    }

    private fun setupShowInfo(showInformation: Show) {
        showLoading(false)
        binding.contentContainer.viewVisibility(true)
        binding.popularShowContainer.mostPopularShowTitle.text = showInformation.name
        binding.popularShowContainer.popularShowImg.setBackEndImage(
            this@DescriptionActivity,
            ImageUrlBuilder.buildBackdropUrl(showInformation.backdropPath ?: "")
        )
        binding.seasonsList.apply {
            layoutManager = LinearLayoutManager(
                this@DescriptionActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = DescriptionSeasonAdapter(showInformation.season as List<Season>)
        }
    }

    private fun showLoading(isVisible: Boolean) {
        binding.loading.viewVisibility(isVisible)
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
        private const val DEFAULT_VALUE_SHOW_ID = 0
    }
}