package com.semicolon.domain.entity.exercise

data class DailyExerciseEntity(
    val stepCount: Int,
    val exerciseTimeAsMilli: Long,
    val traveledDistanceAsMeter: Int,
    val burnedKilocalories: Float
)