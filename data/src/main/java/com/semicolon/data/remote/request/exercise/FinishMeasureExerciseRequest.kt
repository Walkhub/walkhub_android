package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName

data class FinishMeasureExerciseRequest(
    @SerializedName("walk_count") val walkCount: Int,
    @SerializedName("distance") val distanceAsCentimeter: Int
)
