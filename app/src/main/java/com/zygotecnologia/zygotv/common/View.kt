package com.zygotecnologia.zygotv.common

import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

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

fun View.showAnimate(parentView: LinearLayout) {
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