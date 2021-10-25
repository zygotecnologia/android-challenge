package com.zygotecnologia.zygotv.main.presentation

import android.text.ParcelableSpan
import android.text.SpannableString

fun SpannableString.highlightText(
    text: String,
    withStyle: ParcelableSpan
): SpannableString {
    val start = indexOf(text)
    val end = start + text.length

    setSpan(withStyle, start, end, 0)

    return this
}
