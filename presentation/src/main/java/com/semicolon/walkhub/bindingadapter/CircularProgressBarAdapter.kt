package com.semicolon.walkhub.bindingadapter

import androidx.databinding.BindingAdapter
import com.semicolon.walkhub.customview.CircularProgressBar

@BindingAdapter("android:progress")
fun setProgress(view: CircularProgressBar, progress: Float) {
    view.progress = progress
}

@BindingAdapter("android:max")
fun setProgressMax(view: CircularProgressBar, progressMax: Float) {
    view.progressMax = progressMax
}