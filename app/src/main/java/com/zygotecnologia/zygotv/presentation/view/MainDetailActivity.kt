package com.zygotecnologia.zygotv.presentation.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityMainDetailBinding
import com.zygotecnologia.zygotv.domain.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder


class MainDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainDetailBinding
    var show: Show? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        show= intent.extras?.getParcelable(PARAM_SHOW)
        setupView(show)
        setupToolbar()
    }

    private fun setupView(show:Show?) {
        Glide.with(this)
            .load(show?.backdropPath?.let { ImageUrlBuilder.buildBackdropUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.ivShowPoster)
        binding.title.text = show?.originalName
        binding.voteCount.text = show?.voteCount.toString()
        binding.overview.text = show?.overview
    }

    private fun setupToolbar() {
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }

    companion object {
        val PARAM_SHOW = "show_param"
    }
}