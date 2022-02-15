package com.semicolon.domain.entity.exercise

data class ExerciseRecordEntity(
    val exerciseId: Int,
    val imageUrl: String,
    val startAt: String,
    val latitude: Double,
    val longitude: Double
)