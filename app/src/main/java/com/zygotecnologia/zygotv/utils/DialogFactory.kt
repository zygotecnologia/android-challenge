package com.zygotecnologia.zygotv.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.zygotecnologia.zygotv.R
import com.zygotecnologia.zygotv.databinding.LayoutInputDialogBinding
import kotlinx.android.synthetic.main.layout_input_dialog.*

object DialogFactory {

    fun showAlertDialog(appContext: Context, title: String, message: String, buttonText: String? = "Ok") {
        AlertDialog.Builder(appContext).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonText, null)
            create().show()
        }
    }


}