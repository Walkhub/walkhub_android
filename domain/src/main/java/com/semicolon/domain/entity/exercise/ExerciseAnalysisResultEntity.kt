package com.semicolon.domain.entity.exercise

data class ExerciseAnalysisResultEntity(
    val levelId: Int,
    val walkCountList: List<Int>,
    val dailyWalkCountGoal: Int,
    val walkCount: Int,
    val calories: Int,
    val distanceAsKiloMeter: Float,
    val walkTimeAsMinute: Int
)
