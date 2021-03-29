package com.zygotecnologia.zygotv.views.details

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.utils.BaseViewStates
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : BaseViewStates() {

    companion object {
        val DETAILS_SHOW_ID_KEY = "details_show_id_key"
    }

    private lateinit var viewModel: DetailsViewModel

    private val detailsList: RecyclerView by lazy { findViewById(R.id.details_content_list) }

    private var showId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val backButton: ImageView = findViewById(R.id.app_toolbar_back_button)
        backButton.visibility = View.VISIBLE
        backButton.setOnClickListener { finish() }

        this.showId = intent.getIntExtra(DETAILS_SHOW_ID_KEY, -1)

        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        viewModel.loadShowId(showId).observe(this, {
            detailsList.adapter = ShowDetailsAdapter(it)
        })

        viewModel.screenState.observe(this, {
            super.onStateChange(it)
        })
    }

    override fun getContent(): View = detailsList

    override fun retryAction() {
        viewModel.loadShowId(showId)
    }
}