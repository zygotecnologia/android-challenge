package com.zygotecnologia.zygotv.view.description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityShowDescriptionBinding
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val showInformation = intent.getSerializableExtra("ShowInformation") as Show

        binding.popularShowContainer.mostPopularShowTitle.text = showInformation.name

        Glide.with(this)
            .load(showInformation.posterPath?.let { ImageUrlBuilder.buildPosterUrl(it) })
            .apply(RequestOptions().placeholder(R.drawable.image_placeholder))
            .into(binding.popularShowContainer.popularShowImg)
    }
}