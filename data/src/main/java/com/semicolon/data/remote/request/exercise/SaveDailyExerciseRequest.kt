package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName

data class SaveDailyExerciseRequest(
    @SerializedName("distance") val distanceAsCentimeter: Int,
    @SerializedName("walk_count") val walkCount: Int
)
