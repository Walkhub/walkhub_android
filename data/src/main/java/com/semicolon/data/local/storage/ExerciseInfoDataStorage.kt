package com.semicolon.data.local.storage

import com.semicolon.domain.enum.MeasuringState

interface ExerciseInfoDataStorage {

    fun setStartTime(timeAsSecond: Long)

    fun fetchStartTime(): Long

    fun setExerciseId(id: Int)

    fun fetchExerciseId(): Int

    fun setIsMeasuring(isMeasuring: MeasuringState)

    fun isMeasuring(): MeasuringState
}