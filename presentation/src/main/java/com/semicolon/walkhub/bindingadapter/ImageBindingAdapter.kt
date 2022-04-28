package com.semicolon.walkhub.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("image_url")
fun ImageView.loadImageByImageUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}