package com.semicolon.data.remote.response.users

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity

data class FetchCaloriesLevelResponse(
    @SerializedName("calories_level_list") val caloriesLevelList: List<CaloriesLevel>
) {
    data class CaloriesLevel(
        @SerializedName("calorie") val calorie: Int,
        @SerializedName("food_image_url") val foodImageUrl: String,
        @SerializedName("food_name") val foodName: String,
        @SerializedName("level") val level: Int,
        @SerializedName("level_id") val levelId: Int,
        @SerializedName("message") val message: String,
        @SerializedName("size") val size: String
    )
    fun CaloriesLevel.toEntity() =
        FetchCaloriesLevelEntity.CaloriesLevel(
            calorie = calorie,
            foodImageUrl = foodImageUrl,
            foodName = foodName,
            level = level,
            levelId = levelId,
            message = message,
            size = size
        )
}

fun FetchCaloriesLevelResponse.toEntity() =
    FetchCaloriesLevelEntity(
        caloriesLevelList = caloriesLevelList.map { it.toEntity() }
    )