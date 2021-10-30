package com.zygotecnologia.zygotv.view.description

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.databinding.ActivityShowDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShowDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}