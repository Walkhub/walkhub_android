package com.semicolon.walkhub.util

import android.media.Image
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.semicolon.walkhub.R

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

fun EditText.onTextChanged(block: (s: CharSequence?, start: Int, before: Int, count: Int) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            block(s, start, before, count)
        }
    })
}

fun ImageView.setRankImage(rank: Int) {
    when(rank) {
        1 -> {
            this.visible()
            this.setImageResource(R.drawable.ic_rank_first)
        }
        2 -> {
            this.visible()
            this.setImageResource(R.drawable.ic_rank_second)
        }
        3 -> {
            this.visible()
            this.setImageResource(R.drawable.ic_rank_third)
        }
    }
}