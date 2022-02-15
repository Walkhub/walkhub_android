package com.semicolon.domain.entity.users

data class FetchCaloriesLevelEntity(
    val caloriesLevelList: List<CaloriesLevel>
) {
    data class CaloriesLevel(
        val calorie: Int,
        val foodImageUrl: String,
        val foodName: String,
        val level: Int,
        val levelId: Int,
        val message: String,
        val size: String
    )
}