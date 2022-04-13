package com.semicolon.walkhub.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("rank_text")
fun TextView.rankText(rank: Int) {
    val rankText = "${rank}등"
    text = rankText
}