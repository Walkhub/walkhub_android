package com.semicolon.data.local.storage

import com.google.android.gms.fitness.result.DataReadResponse
import com.google.android.gms.tasks.Task
import com.semicolon.data.local.param.PeriodParam

interface FitnessDataStorage {

    suspend fun fetchExerciseRecord(periodParam: PeriodParam): Task<DataReadResponse>

    suspend fun fetchLocationRecord(periodParam: PeriodParam): Task<DataReadResponse>

    suspend fun fetchWalkRecord(periodParam: PeriodParam): Task<DataReadResponse>
}