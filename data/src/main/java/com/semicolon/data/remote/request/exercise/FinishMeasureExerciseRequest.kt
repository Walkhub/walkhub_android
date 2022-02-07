package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName

data class FinishMeasureExerciseRequest(
    @SerializedName("walk_count") val walkCount: Int,
    @SerializedName("distance") val distanceAsCentimeter: Int,
    @SerializedName("calorie") val kilocalorie: Int,
    @SerializedName("image_url") val imageUrl: String
)
