package com.semicolon.data.local.datasource

import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import kotlinx.coroutines.flow.Flow

interface LocalExerciseDataSource {

    suspend fun fetchDailyExerciseRecordAsFlow(): Flow<DailyExerciseEntity>

    suspend fun fetchLocationRecord(periodParam: PeriodParam): List<LocationRecordEntity>

    suspend fun fetchWalkRecord(periodParam: PeriodParam): WalkRecordEntity
}