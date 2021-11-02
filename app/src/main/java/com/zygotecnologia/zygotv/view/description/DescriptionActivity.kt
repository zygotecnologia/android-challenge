package com.zygotecnologia.zygotv.view.description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.adapter.description.DescriptionSeasonAdapter
import com.zygotecnologia.zygotv.databinding.ActivityShowDescriptionBinding
import com.zygotecnologia.zygotv.model.Episodes
import com.zygotecnologia.zygotv.model.Season
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.network.TmdbClient
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder
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

    private lateinit var descriptionSeasonAdapter: DescriptionSeasonAdapter

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

        viewModel.fetchShowDescription(intent.getIntExtra(SHOW_ID_KEY, DEFAULT_VALUE_SHOW_ID))
    }

    private fun setupObservers() {
        viewModel.viewState.observe(this, {
            when (it) {
                is DescriptionViewState.ShowAndSeasonsDescriptions -> setupShowInfo(it.show)
            }
        })
    }

    private fun setupShowInfo(showInformation: Show) {
        binding.popularShowContainer.mostPopularShowTitle.text = showInformation.name

        Glide.with(this)
            .load(showInformation.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.popularShowContainer.popularShowImg)

        descriptionSeasonAdapter = DescriptionSeasonAdapter(
            showInformation.season as List<Season>
        )

        binding.seasonsList.apply {
            layoutManager = LinearLayoutManager(
                this@DescriptionActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = descriptionSeasonAdapter
        }
    }

    companion object {
        private const val SHOW_ID_KEY = "ShowID"
        private const val DEFAULT_VALUE_SHOW_ID = 0
    }
}