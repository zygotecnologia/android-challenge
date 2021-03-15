package com.zygotecnologia.zygotv.common

import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.zygotecnologia.zygotv.R


/**
 * @author allef.santos on 13/03/21
 */

fun View.showOrHide(showOrHide: Boolean?) {
    showOrHide?.let { if (showOrHide) visible() else gone() }
}

fun View.invisible() = changeVisibility(this, View.INVISIBLE)

fun View.gone() = changeVisibility(this, View.GONE)

fun View.visible() = changeVisibility(this, View.VISIBLE)

fun View.isVisible() = visibility == View.VISIBLE

private fun changeVisibility(view: View, visibility: Int) {
    view.visibility = visibility
}

fun View.showAnimate(parentView: ViewGroup) {
    val transition: Transition = Fade()
    transition.duration = 900
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(parentView, transition)
    visibility = View.VISIBLE
}
fun View.hideAnimate(parentView: ConstraintLayout) {
    val transition: Transition = Fade()
    transition.duration = 900
    transition.addTarget(this)
    TransitionManager.beginDelayedTransition(parentView, transition)
    visibility = View.GONE
}


fun View.showSnackBar(root: View = this, snackTitle: String,length:Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(root, snackTitle,length)
    val view = snackbar.view
    view.setBackgroundColor(ContextCompat.getColor(this.context, R.color.toolbar_title_color))
    snackbar.show()
    val txtv = view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
    txtv.setTextColor(ContextCompat.getColor(this.context, R.color.white))
    txtv.gravity = Gravity.CENTER_HORIZONTAL
}