package com.semicolon.walkhub.ui.cheering

abstract class CheeringItemViewModel(
    val userName: String,
    val imageUrl: String
) {
    abstract fun onClick()
}