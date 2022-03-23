package com.semicolon.data.local.storage

import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.enums.MeasuringState

interface ExerciseInfoDataStorage {

    fun setFirstStartTime(timeAsSecond: Long)

    fun fetchFirstStartTime(): Long

    fun setStartTime(timeAsSecond: Long)

    fun fetchStartTime(): Long

    fun setPausedTime(timeAsSecond: Long)

    fun fetchPausedTime(): Long

    fun setExerciseId(id: Int)

    fun fetchExerciseId(): Int

    fun setIsMeasuring(isMeasuring: MeasuringState)

    fun isMeasuring(): MeasuringState

    fun setGoal(goalEntity: GoalEntity)

    fun fetchGoal(): GoalEntity
}