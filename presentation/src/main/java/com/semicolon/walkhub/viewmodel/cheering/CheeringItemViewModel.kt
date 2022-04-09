package com.semicolon.walkhub.viewmodel.cheering

abstract class CheeringItemViewModel(
    val id: Int,
    val userName: String,
    val imageUrl: String
) {
    abstract fun onClick()
}