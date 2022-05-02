package com.semicolon.walkhub.bindingadapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("goal_text")
fun TextView.goalText(goal: Int) {
    val goalText = "$goal 걸을"
    text = goalText
}

@BindingAdapter("current_text")
fun TextView.currentText(current: Int) {
    val currentText = "현재 $current 걸음"
    text = currentText
}

@BindingAdapter("goal_is_distance", "goal_value")
fun TextView.goalDistanceText(isDistance: Boolean, goal: Int) {
    val unit = if (isDistance) "km" else "걸음"
    val goalText = if (isDistance) goal / 1000 else goal
    val goalDistance = "/$goalText $unit"
    text = goalDistance
}