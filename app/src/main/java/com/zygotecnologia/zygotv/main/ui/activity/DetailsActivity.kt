package com.zygotecnologia.zygotv.main.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.databinding.ActivityDetailsBinding
import com.zygotecnologia.zygotv.main.adapters.DetailsAdapter
import com.zygotecnologia.zygotv.main.viewModel.DetailsViewModel
import com.zygotecnologia.zygotv.model.season.SeasonResponse
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadBackDrop
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadPoster
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : BaseActivity() {

    companion object {
        const val ID_INTENT_EXTRA = "id"
        const val TITLE_INTENT_EXTRA = "title"
        const val BANNER_INTENT_EXTRA = "banner"
    }

    override val viewModel: DetailsViewModel by viewModel()

    override val loading: View by lazy { binding.loading.root }
    override val mainContent: View by lazy { binding.mainContent }
    override val toolbarText: TextView by lazy { binding.toolbarText }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        fetchDetails()
        setupToolbar()
        setupHeader()
    }

    override fun setupObservers() {
        super.setupObservers()
        showDetailsLoadedObserver()
        seasonDetailsLoadedObserver()
    }

    private fun seasonDetailsLoadedObserver() {
        viewModel.eventDataLoaded.observe(this, Observer { hasLoaded ->
            if(hasLoaded) {
                viewModel.seasons.let {
                    setupSeasons(it)
                }
            }
        })
    }

    private fun showDetailsLoadedObserver() {
        viewModel.showDetails.observe(this, Observer {
            it?.let {
                intent.extras?.getInt(ID_INTENT_EXTRA)?.let { id ->
                    viewModel.loadSeasonsDetails(id)
                }
            }
        })
    }

    private fun setupSeasons(seasonsList: List<SeasonResponse>) {
        binding.rvSeasonsList.adapter = DetailsAdapter(seasonsList)
    }

    private fun fetchDetails() {
        intent.extras?.getInt(ID_INTENT_EXTRA)?.let {
            viewModel.loadShowDetails(it)
        }
    }

    private fun setupHeader() {
        binding.tvShowTitle.text = intent.extras?.getString(TITLE_INTENT_EXTRA)
        intent.extras?.getString(BANNER_INTENT_EXTRA)?.loadBackDrop(binding.root, binding.banner)
    }

    override fun setupToolbar() {
        super.setupToolbar()
        setupToolbarBackButton()
    }

    private fun setupToolbarBackButton() {
        binding.ivIconBack.setOnClickListener {
            this.finish()
        }
    }


}