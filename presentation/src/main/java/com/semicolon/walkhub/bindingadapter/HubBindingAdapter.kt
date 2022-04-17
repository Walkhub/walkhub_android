package com.semicolon.walkhub.bindingadapter

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel

@BindingAdapter("rank_text")
fun TextView.rankText(rank: Int?) {
    text = if (rank != null) {
        "${rank}등"
    } else {
        "0등"
    }
}

@BindingAdapter("walk_text")
fun TextView.walkText(walkCount: Int) {
    val walkText = "$walkCount 걸음"
    text = walkText
}

@BindingAdapter("walk_percent")
fun ProgressBar.walkPercent(item: HubUserViewModel.HubMyPageItem?) {
    progress = if(item != null) {
        ((item.myWalkCount - item.downWalkCount).toDouble() / (item.topWalkCount - item.downWalkCount).toDouble() * 100).toInt()
    } else { 0 }
}

@BindingAdapter("need_walk_text")
fun TextView.needWalkText(item: HubUserViewModel.HubMyPageItem?) {
    text = if (item?.myPageData != null) {
        "다음 등수까지 ${item.topWalkCount - item.myWalkCount} 걸음"
    } else {
        "현재 소속되어 있는 반이 없어요."
    }
}

@BindingAdapter("next_walk_text")
fun TextView.nextWalkText(topWalkCount: Int) {
    val needWalkText = "$topWalkCount 걸음"
    text = needWalkText
}