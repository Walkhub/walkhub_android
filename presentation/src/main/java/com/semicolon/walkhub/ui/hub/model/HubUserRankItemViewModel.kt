package com.semicolon.walkhub.ui.hub.model

abstract class HubUserRankItemViewModel(
    val profileUrl: String,
    val name: String,
    val walkCount: Int,
    val rank: Int
) {
    abstract fun onClick()
}