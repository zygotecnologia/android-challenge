package com.zygotecnologia.zygotv.utils

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.toHTML(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun String.removeOutsideParagraph() = this.removePrefix("<p>").removeSuffix("</p>").toHTML()