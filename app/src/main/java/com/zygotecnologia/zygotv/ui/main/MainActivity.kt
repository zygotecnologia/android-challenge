package com.zygotecnologia.zygotv.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.utils.ConnectionLiveData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}