package com.zygotecnologia.zygotv.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.presentation.adapter.DescAdapter
import kotlinx.android.synthetic.main.activity_detail2.*
import kotlinx.android.synthetic.main.item_show.*
import kotlinx.android.synthetic.main.show.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

//        intent.putExtra("originalName", movie.originalName)
//        intent.putExtra("poster", movie.posterPath)
//        intent.putExtra("name", movie.name)
//        intent.putExtra("backdropPath", movie.backdropPath)
//        intent.putExtra("genre", movie.overview)

        lateinit var txtTitleDesc: TextView
       // lateinit var txtTitleMDesc: TextView
        lateinit var plot: TextView
        lateinit var genre: TextView
        lateinit var imgSerieDesc: ImageView

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)

//        imgSerieDesc = img_show

        val title = intent.getStringExtra("originalName")
//        val titleDesc = intent.getStringExtra("title")
//        val plotDesc = intent.getStringExtra("plot")
////        val genreDesc = intent.getStringExtra("genre")
//
          txtTitleDesc.text = title
//        title.text = titleDesc
//        plot.text = plotDesc
//        genre.text = genreDesc
//
//        val intent = intent
//        val url = intent.getStringExtra("imgMovie")
//        Picasso.get().load(url).into(imgMovie)
    }
}