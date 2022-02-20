package com.semicolon.data.remote.response.level

import com.google.gson.annotations.SerializedName

data class LevelListResponse(@SerializedName("calories_level_list") val caloriesLevelList: List<LevelResponse>) {
    data class LevelResponse(
        @SerializedName("level_id") val levelId: Int,
        @SerializedName("food_image_url") val foodImageUrl: String,
        @SerializedName("food_name") val foodName: String,
        @SerializedName("calorie") val calorie: String,
        @SerializedName("size") val size: String,
        @SerializedName("level") val level: String,
        @SerializedName("message") val message: String
    )
}