package com.zygotecnologia.zygotv.showdetails.view

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.core.ImageLoader
import com.zygotecnologia.zygotv.showdetails.R
import com.zygotecnologia.zygotv.showdetails.presenter.ShowDetailsPresenterMock
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity: AppCompatActivity() {

    private val presenter = ShowDetailsPresenterMock()
    private var showId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)
        showId = intent.getIntExtra(SHOW_ID_EXTRA, 0)
        presenter.getShowDetailsObserver().observe(this, showDetailsObserver)
    }

    private val showDetailsObserver by lazy {
        Observer<ShowDetailsViewModel> { showDetailsViewModel ->
            showName.text  = showDetailsViewModel.name
            ImageLoader.loadImage(showBackDrop, showDetailsViewModel.backdropUrl)

        }
    }

    companion object {
        const val SHOW_ID_EXTRA = "showId"
    }

}