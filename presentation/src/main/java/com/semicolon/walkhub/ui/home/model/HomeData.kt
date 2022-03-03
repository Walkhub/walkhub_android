package com.semicolon.walkhub.ui.home.model

data class HomeData(
    val stepCount: Int,
    val exerciseTimeAsMilli: Long,
    val traveledDistanceAsMeter: Int,
    val burnedKilocalories: Float
)
