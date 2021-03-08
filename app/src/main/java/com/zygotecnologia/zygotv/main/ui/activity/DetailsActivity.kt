package com.zygotecnologia.zygotv.main.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityDetailsBinding
import com.zygotecnologia.zygotv.main.adapters.DetailsAdapter
import com.zygotecnologia.zygotv.main.viewModel.DetailsViewModel
import com.zygotecnologia.zygotv.model.SeasonResponse
import com.zygotecnologia.zygotv.utils.DialogFactory
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.toHTML
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val ID_INTENT_EXTRA = "id"
        const val TITLE_INTENT_EXTRA = "title"
        const val BANNER_INTENT_EXTRA = "banner"
    }

    private val viewModel: DetailsViewModel by viewModel()
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

    private fun setupObservers() {
        errorDialogObserver()
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
        intent.extras?.getString(BANNER_INTENT_EXTRA)?.loadImage(binding.root, binding.banner)
    }

    private fun setupToolbar() {
        setupToolbarText()
        setupToolbarBackButton()
    }

    private fun setupToolbarBackButton() {
        binding.ivIconBack.setOnClickListener {
            this.finish()
        }
    }

    private fun setupToolbarText() {
        binding.toolbarText.text = resources.getString(R.string.toolbar_text).toHTML()
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