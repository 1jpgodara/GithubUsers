package com.jp.githubusers.util

import android.view.View

fun View.isVisible() = visibility == View.VISIBLE

fun View.isGone() = visibility == View.GONE

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.hidden() {
    visibility = View.INVISIBLE
}
