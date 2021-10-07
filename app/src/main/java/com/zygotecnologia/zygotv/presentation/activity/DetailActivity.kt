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
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_desc.*
import kotlinx.android.synthetic.main.item_show.*
import kotlinx.android.synthetic.main.show.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail2)


        val title = intent.getStringExtra("name")

         txtTitleDesc.text = title

        val intent = intent
        val url = intent.getStringExtra("backdropPath")
        Picasso.get().load(url).into(imgSerieDesc)

        val uri = intent.getStringExtra("poster")
        Picasso.get().load(uri).into(imgPosterDesc)

    }
}