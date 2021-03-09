package com.zygotecnologia.zygotv.main.ui.activity

import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.main.viewModel.BaseViewModel
import com.zygotecnologia.zygotv.main.viewModel.MainViewModel
import com.zygotecnologia.zygotv.utils.DialogFactory
import com.zygotecnologia.zygotv.utils.gone
import com.zygotecnologia.zygotv.utils.toHTML
import com.zygotecnologia.zygotv.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

abstract class BaseActivity : AppCompatActivity() {

    open  val viewModel: BaseViewModel by viewModel()

    abstract val loading : View
    abstract val mainContent : View
    abstract val toolbarText : TextView

    protected open fun setupObservers() {
        loadingObserver()
        errorDialogObserver()
    }

    protected open fun setupToolbar() {
        setupToolbarText()
    }

    private fun loadingObserver() {
        viewModel.loading.observe(this, Observer { value ->
            toogleLoading(value)
        })
    }

    private fun toogleLoading(value: Boolean) {
        if (value) {
            loading.visible()
            mainContent.gone()
        } else {
            loading.gone()
            mainContent.visible()
        }
    }

    private fun setupToolbarText() {
        toolbarText.text = resources.getString(R.string.toolbar_text).toHTML()
    }

    private fun errorDialogObserver() {
        viewModel.errorDialog.observe(this, Observer { error ->
            error?.let {
                DialogFactory.showAlertDialog(
                    this,
                    error.title,
                    error.message,
                    { finish() }
                )
            }
        })
    }

}