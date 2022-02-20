package com.semicolon.data.remote.response.level

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.level.LevelEntity

data class LevelListResponse(@SerializedName("calories_level_list") val caloriesLevelList: List<LevelResponse>) {
    data class LevelResponse(
        @SerializedName("level_id") val levelId: Int,
        @SerializedName("food_image_url") val foodImageUrl: String,
        @SerializedName("food_name") val foodName: String,
        @SerializedName("calorie") val calorie: Int,
        @SerializedName("size") val size: String,
        @SerializedName("level") val level: Int,
        @SerializedName("message") val message: String
    )
}

fun LevelListResponse.toEntity(): List<LevelEntity> =
    caloriesLevelList.map {
        LevelEntity(
            levelId = it.levelId,
            foodImageUrl = it.foodImageUrl,
            foodName = it.foodName,
            calories = it.calorie,
            size = it.size,
            level = it.level,
            message = it.message
        )
    }