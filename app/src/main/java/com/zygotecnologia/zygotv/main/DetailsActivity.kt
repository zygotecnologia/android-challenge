package com.zygotecnologia.zygotv.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.ActivityDetailsBinding
import com.zygotecnologia.zygotv.utils.ImageUrlBuilder.loadImage
import com.zygotecnologia.zygotv.utils.toHTML

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val TITLE_INTENT_EXTRA = "title"
        const val BANNER_INTENT_EXTRA = "banner"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupToolbar()
        setupHeader()
    }

    private fun setupHeader() {
        binding.tvShowTitle.text = intent.extras?.getString(TITLE_INTENT_EXTRA)
        intent.extras?.getString(BANNER_INTENT_EXTRA)?.loadImage(binding.root, binding.banner)
    }

    private fun setupToolbar() {
        setupToolbarText()
        setupToolbarBackButton()
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