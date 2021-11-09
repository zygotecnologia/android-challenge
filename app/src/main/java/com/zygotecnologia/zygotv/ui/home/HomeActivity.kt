package com.zygotecnologia.zygotv.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}