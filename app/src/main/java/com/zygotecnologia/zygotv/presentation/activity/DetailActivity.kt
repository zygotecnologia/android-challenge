package com.zygotecnologia.zygotv.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zygotecnologia.zygotv.R
import kotlinx.android.synthetic.main.activity_detail2.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)


        val title = intent.getStringExtra("name")

         txtTitleDesc.text = title

    }

    override fun onResume() {
        super.onResume()

        val intent = intent
        val url = intent.getStringExtra("backdropPath")
        Glide.with(imgSerieDesc.context).load(url).into(imgSerieDesc)
    }
}