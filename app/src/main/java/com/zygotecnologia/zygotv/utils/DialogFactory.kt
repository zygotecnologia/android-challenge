package com.zygotecnologia.zygotv.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.*
import com.zygotecnologia.zygotv.R
import kotlinx.android.synthetic.main.layout_custom_dialog.bt
import kotlinx.android.synthetic.main.layout_custom_dialog.tv_custom_dialog_message
import kotlinx.android.synthetic.main.layout_custom_dialog.tv_custom_dialog_title

object DialogFactory {

    open class CustomDialog(
        appContext: Context,
        private val title: String,
        private val message: String,
        private val actionButtonMsg: String? = "Ok",
        private val action: (() -> Unit)? = null,
        private val forceNonDismiss: Boolean = false
    ) : Dialog(appContext, R.style.CustomDialogStyle) {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            window?.setBackgroundDrawableResource(android.R.color.transparent)
            setContentView(R.layout.layout_custom_dialog)
            setupViews()
            setupClickListener()
            setupListener()
        }

        private fun setupViews() {
            tv_custom_dialog_title.text = title
            tv_custom_dialog_message.text = message
            bt.text = actionButtonMsg
        }

        private fun setupClickListener() {
            bt.setOnClickListener {
                if (forceNonDismiss)
                    action?.invoke()
                else
                    dismiss()
            }
        }

        private fun setupListener() {
            setOnDismissListener {
                action?.invoke()
            }
            setOnCancelListener {
                action?.invoke()
            }
        }
    }
}