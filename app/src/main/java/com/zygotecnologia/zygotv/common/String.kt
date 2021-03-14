package com.zygotecnologia.zygotv.common

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun TextView.colorize(subStringToColorize: String, @ColorRes colorResId: Int) {

    val spannable: Spannable = SpannableString(text)

    val startIndex = text.indexOf(subStringToColorize, startIndex = 0, ignoreCase = false)
    val endIndex = startIndex + subStringToColorize.length

    val color: Int = ContextCompat.getColor(context, colorResId)

    if (startIndex != -1) {
        spannable.setSpan(
            ForegroundColorSpan(color),
            startIndex,
            endIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        setText(spannable, TextView.BufferType.SPANNABLE)
    }
}