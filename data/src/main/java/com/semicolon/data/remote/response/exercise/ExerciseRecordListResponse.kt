package com.semicolon.data.remote.response.exercise

import com.google.gson.annotations.SerializedName
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity

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

fun ExerciseRecordListResponse.ExerciseRecord.toEntity() =
    ExerciseRecordEntity(
        exerciseId = exerciseId,
        imageUrl = imageUrl,
        startAt = startAt,
        latitude = latitude,
        longitude = longitude
    )

fun ExerciseRecordListResponse.toEntityList() =
    this.exerciseRecordList.map { it.toEntity() }
