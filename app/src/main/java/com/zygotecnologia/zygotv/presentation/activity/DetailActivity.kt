package com.zygotecnologia.zygotv.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zygotecnologia.zygotv.databinding.ActivityDetail2Binding
import com.zygotecnologia.zygotv.presentation.series.FragmentSeries
import kotlinx.android.synthetic.main.activity_detail2.*

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetail2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetail2Binding.inflate(layoutInflater)
            setContentView(binding.root)

        val title = intent.getStringExtra("name")
        val description = intent.getStringExtra("overview")

        binding.txtTitleDesc.text = title
        binding.txtDescription.text = description

    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        val url = intent.getStringExtra("backdropPath")
        Glide.with(imgSerieDesc.context).load(url).into(imgSerieDesc)

        val uri = intent.getStringExtra("poster")
        Glide.with(imgPosterDesc.context).load(uri).into(imgPosterDesc)

        startFragment()

    }

    fun startFragment(){
        binding.imgArrowLeft.setOnClickListener {
            val intent = Intent(this, FragmentSeries::class.java)
            startActivity(intent)
        }
    }
}