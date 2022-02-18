package com.semicolon.walkhub.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.GONE
}

fun ImageView.loadFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .into(this)

fun ImageView.loadCircleFromUrl(url: String) =
    Glide.with(this.context.applicationContext)
        .load(url)
        .circleCrop()
        .into(this)