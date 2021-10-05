package com.zygotecnologia.zygotv.presentation.activity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zygotecnologia.zygotv.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var actor: TextView
        lateinit var title: TextView
        lateinit var plot: TextView
        lateinit var genre: TextView
        lateinit var imgMovie: ImageView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

        actor = findViewById(R.id.tv_title_movie)
        title = findViewById(R.id.txtCategories)
        imgMovie = findViewById(R.id.img_serie)

        val value = intent.getStringExtra("actor")
        val titleDesc = intent.getStringExtra("title")
        val plotDesc = intent.getStringExtra("plot")
        val genreDesc = intent.getStringExtra("genre")

        actor.text = value
        title.text = titleDesc
        plot.text = plotDesc
        genre.text = genreDesc

        val intent = intent
        val url = intent.getStringExtra("imgMovie")
        Picasso.get().load(url).into(imgMovie)
    }
}