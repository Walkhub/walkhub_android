package com.semicolon.data.local.storage

import com.semicolon.data.local.entity.exercise.WalkRecordEntity

interface FitnessAccumulateDataStorage {

    fun accumulate(walkRecord: WalkRecordEntity)

    fun fetchAccumulatedData(): WalkRecordEntity

    fun clearAccumulatedData()

    fun addPausedTime(timeAsSecond: Long)

    fun fetchPausedTime(): Long

    fun clearPausedTime()
}