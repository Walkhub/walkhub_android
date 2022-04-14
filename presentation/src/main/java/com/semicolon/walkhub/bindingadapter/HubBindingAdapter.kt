package com.semicolon.walkhub.bindingadapter

import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel
import org.w3c.dom.Text

@BindingAdapter("rank_text")
fun TextView.rankText(rank: Int) {
    val rankText = "${rank}등"
    text = rankText
}

@BindingAdapter("walk_text")
fun TextView.walkText(walkCount: Int) {
    val walkText = "$walkCount 걸음"
    text = walkText
}

@BindingAdapter("walk_percent")
fun ProgressBar.walkPercent(item: HubUserViewModel.HubMyPageItem) {
    item.run {
        progress =
            ((topWalkCount - downWalkCount).toDouble() / (topWalkCount - downWalkCount).toDouble() * 100).toInt()
    }
}

@BindingAdapter("need_walk_text")
fun TextView.needWalkText(item: HubUserViewModel.HubMyPageItem) {
    item.run {
        var needWalkText = "현재 소속되어 있는 반이 없어요."
        if(myWalkCount != 0) {
            needWalkText = "${topWalkCount-myWalkCount} 걸음"
        }

        text = needWalkText
    }
}

@BindingAdapter("next_walk_text")
fun TextView.nextWalkText(topWalkCount: Int) {
    val needWalkText = "$topWalkCount 걸음"
    text = needWalkText
}