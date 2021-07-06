package com.zygotecnologia.zygotv.showdetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.core.ImageLoader
import com.zygotecnologia.zygotv.showdetails.R
import com.zygotecnologia.zygotv.showdetails.controller.ShowDetailsController
import com.zygotecnologia.zygotv.showdetails.presenter.SeasonsPresenter
import com.zygotecnologia.zygotv.showdetails.presenter.SeasonsPresenterMock
import com.zygotecnologia.zygotv.showdetails.presenter.ShowDetailsPresenter
import com.zygotecnologia.zygotv.showdetails.presenter.ShowDetailsPresenterMock
import com.zygotecnologia.zygotv.showdetails.view.expandablelist.SeasonExpandableListAdapter
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : AppCompatActivity() {

    private val detailsPresenter = ShowDetailsPresenterMock()
    private val seasonsPresenter = SeasonsPresenter.make()
    private lateinit var controller: ShowDetailsController
    private val adapter = SeasonExpandableListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        expandableSeasonList.setAdapter(adapter)
        detailsPresenter.getShowDetailsObserver().observe(this, showDetailsObserver)
        seasonsPresenter.getSeasonsObserver().observe(this, seasonsObserver)
        controller = ShowDetailsController.Builder()
            .setShowDetailsPresenter(detailsPresenter)
            .setShowSeasonsPresenter(seasonsPresenter)
            .build()
        controller.fetchItems(intent.getIntExtra(SHOW_ID_EXTRA, 0))
    }

    private val showDetailsObserver by lazy {
        Observer<ShowDetailsViewModel> { showDetailsViewModel ->
            showName.text = showDetailsViewModel.name
            ImageLoader.loadImage(showBackDrop, showDetailsViewModel.backdropUrl)
        }
    }

    private val seasonsObserver by lazy {
        Observer<List<SeasonViewModel>> { seasons ->
            adapter.setSeasons(seasons)
        }
    }

    companion object {
        const val SHOW_ID_EXTRA = "showId"
    }

}