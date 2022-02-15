package com.autumnsun.suncoinmarket.core.utils


import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun View.toggleAlpha(isShow: Boolean, delay: Long = 200, invisibleMode: Int = View.GONE) {
    if (isShow) animateAlpha(View.VISIBLE, delay) else animateAlpha(invisibleMode, delay)
}

fun View.animateAlpha(visibility: Int, delay: Long = 200) {
    if (visibility == View.VISIBLE) {
        setVisibility(View.VISIBLE)
    }

    val alpha = when (visibility) {
        View.GONE, View.INVISIBLE -> 0f
        View.VISIBLE -> 1f
        else -> 1f
    }

    animate().apply {
        duration = delay
        alpha(alpha)
        withEndAction {
            setVisibility(visibility)
        }
    }
}
