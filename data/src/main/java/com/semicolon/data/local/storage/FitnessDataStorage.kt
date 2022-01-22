package com.semicolon.data.local.storage

import com.google.android.gms.fitness.result.DataReadResponse
import com.google.android.gms.tasks.Task

interface FitnessDataStorage {

    suspend fun fetchExerciseRecord(
        startTimeAsMilli: Long,
        endTimeAsMilli: Long
    ): Task<DataReadResponse>

    suspend fun fetchLocationRecord(
        startTimeAsMilli: Long,
        endTimeAsMilli: Long
    ): Task<DataReadResponse>

    suspend fun fetchWalkRecord(
        startTimeAsMilli: Long,
        endTimeAsMilli: Long
    ): Task<DataReadResponse>
}