package com.zygotecnologia.zygotv.views.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.utils.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private val sectionList: RecyclerView by lazy { findViewById(R.id.main_section_list) }
    private val loadingContainer: ConstraintLayout by lazy { findViewById(R.id.main_progress_container) }
    private val errorContainer: ConstraintLayout by lazy { findViewById(R.id.main_error_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observe sections change & call API
        viewModel.loadSections().observe(this, {
            sectionList.adapter = SectionAdapter(it)
        })

        //Observe screen state to show/hide components
        viewModel.screenState.observe(this, { state ->
            when (state) {
                ScreenState.LOADING -> {
                    loadingContainer.visibility = View.VISIBLE
                    sectionList.visibility = View.GONE
                    errorContainer.visibility = View.GONE
                }
                ScreenState.NETWORK_ERROR -> onError(true)
                ScreenState.GENERIC_ERROR -> onError(false)
                ScreenState.SUCCESS -> {
                    loadingContainer.visibility = View.GONE
                    sectionList.visibility = View.VISIBLE
                    errorContainer.visibility = View.GONE
                }
            }
        })
    }

    private fun onError(isNetworkError: Boolean) {
        loadingContainer.visibility = View.GONE
        sectionList.visibility = View.GONE
        errorContainer.visibility = View.VISIBLE

        val text: TextView = findViewById(R.id.main_error_text)
        val textStringId =
                if (isNetworkError) R.string.network_error_message
                else R.string.generic_error_message

        text.text = getString(textStringId)

        val button: Button = findViewById(R.id.main_error_button)
        button.setOnClickListener { viewModel.loadSections() }
    }

}