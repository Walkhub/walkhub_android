package com.semicolon.data.remote.response.exercise

import com.google.gson.annotations.SerializedName

data class ExerciseRecordListResponse(
    @SerializedName("exercise_list") val exerciseRecordList: List<ExerciseRecord>
) {
    data class ExerciseRecord(
        @SerializedName("exercise_id") val exerciseId: Int,
        @SerializedName("image_url") val imageUrl: String,
        @SerializedName("start_at") val startAt: String,
        @SerializedName("latitude") val latitude: Double,
        @SerializedName("longitude") val longitude: Double
    )
}
