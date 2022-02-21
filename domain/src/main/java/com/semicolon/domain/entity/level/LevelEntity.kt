package com.semicolon.domain.entity.level

data class LevelEntity(
    val levelId: Int,
    val foodImageUrl: String,
    val foodName: String,
    val calories: Int,
    val size: String,
    val level: Int,
    val message: String
)