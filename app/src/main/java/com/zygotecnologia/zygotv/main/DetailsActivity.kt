package com.zygotecnologia.zygotv.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityDetailsBinding
import com.zygotecnologia.zygotv.databinding.ActivityMainBinding
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.model.Show
import com.zygotecnologia.zygotv.utils.DialogFactory
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.toHTML
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbarText()
        setupToolbarBackButton()

        binding.tvShowTitle.text = intent.extras?.get("title") as CharSequence
        intent.extras?.getString("banner")?.loadImage(binding.root, binding.banner)
    }

    private fun setupToolbarBackButton() {
        binding.ivIconBack.setOnClickListener {
            this.finish()
        }
    }

    private fun setupToolbarText() {
        binding.toolbarText.text = resources.getString(R.string.toolbar_text).toHTML()
    }

/*    private fun errorDialogObserver() {
        viewModel.errorDialog.observe(this, Observer { error ->
            error?.let {
                DialogFactory.showAlertDialog(
                    this,
                    error.title,
                    error.message
                )
            }
        })
    }*/

}