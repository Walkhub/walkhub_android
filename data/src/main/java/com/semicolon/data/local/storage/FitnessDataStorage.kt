package com.semicolon.data.local.storage

import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.result.DataReadResponse
import com.google.android.gms.tasks.Task
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseEntity
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import kotlinx.coroutines.flow.Flow

interface FitnessDataStorage {

    suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity>

    suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity>

    suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity

    suspend fun startRecordExercise(onSuccess: (DataType) -> Unit, onFailure: (DataType) -> Unit)
}