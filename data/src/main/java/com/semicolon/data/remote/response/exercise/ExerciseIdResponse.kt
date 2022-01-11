package com.semicolon.data.remote.response.exercise

import com.google.gson.annotations.SerializedName

data class ExerciseIdResponse(
    @SerializedName("exercise_id") val exerciseId: Int
)