package com.zygotecnologia.zygotv.custom

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import com.zygotecnologia.zygotv.R


class ZygoTextfField : AppCompatTextView {

    constructor(context: Context) : super(context) {
        val typeface = ResourcesCompat.getFont(context, R.font.roboto_medium)
        setTypeface(typeface)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val typeface = ResourcesCompat.getFont(context, R.font.roboto_medium)
        setTypeface(typeface)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val typeface = ResourcesCompat.getFont(context, R.font.roboto_medium)
        setTypeface(typeface)
    }
}