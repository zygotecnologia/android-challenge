package com.zygotecnologia.zygotv.utils.binding

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.zygotecnologia.zygotv.R

object TextViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("formatLogo")
    fun TextView.formatLogo(formatLogo: Boolean?) {
        val logoSpannable = SpannableString(context.getString(R.string.app_name))
        logoSpannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, R.color.red)),
            4,
            6,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        logoSpannable.setSpan(
            StyleSpan(Typeface.BOLD),
            4,
            6,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        this.text = logoSpannable
    }
}