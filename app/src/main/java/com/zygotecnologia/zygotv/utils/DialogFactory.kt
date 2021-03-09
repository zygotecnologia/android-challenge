package com.zygotecnologia.zygotv.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogFactory {

    fun showAlertDialog(appContext: Context, title: String, message: String,
                        dismissAction : () -> Unit, buttonText: String? = "Ok") {
        AlertDialog.Builder(appContext).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(buttonText, null)
            setOnDismissListener { dismissAction() }
            create().show()
        }
    }


}