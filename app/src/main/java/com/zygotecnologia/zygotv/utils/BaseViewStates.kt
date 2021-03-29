package com.zygotecnologia.zygotv.utils

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.zygotecnologia.zygotv.R

/*
 * Basic generic state management to reuse in views
 */

abstract class BaseViewStates: AppCompatActivity() {

    private val loadingContainer: ConstraintLayout by lazy { findViewById(R.id.main_progress_container) }
    private val errorContainer: ConstraintLayout by lazy { findViewById(R.id.main_error_container) }

    //Require the content view from child screen
    abstract fun getContent(): View
    //Require the retryAction on type try again button
    abstract fun retryAction()

    fun onStateChange(state: ScreenState) {
        when (state) {
            ScreenState.LOADING -> {
                loadingContainer.visibility = View.VISIBLE
                getContent().visibility = View.GONE
                errorContainer.visibility = View.GONE
            }
            ScreenState.NETWORK_ERROR -> onError(true)
            ScreenState.GENERIC_ERROR -> onError(false)
            ScreenState.SUCCESS -> {
                loadingContainer.visibility = View.GONE
                getContent().visibility = View.VISIBLE
                errorContainer.visibility = View.GONE
            }
        }
    }

    private fun onError(isNetworkError: Boolean) {
        loadingContainer.visibility = View.GONE
        getContent().visibility = View.GONE
        errorContainer.visibility = View.VISIBLE

        val text: TextView = findViewById(R.id.main_error_text)
        val textStringId =
            if (isNetworkError) R.string.network_error_message
            else R.string.generic_error_message

        text.text = getString(textStringId)

        val button: Button = findViewById(R.id.main_error_button)
        button.setOnClickListener { retryAction() }
    }

}