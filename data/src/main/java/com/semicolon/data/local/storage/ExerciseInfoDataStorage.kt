package com.semicolon.data.local.storage

interface ExerciseInfoDataStorage {

    fun setStartTime(timeAsSecond: Long)

    fun fetchStartTime(): Long

    fun setExerciseId(id: Int)

    fun fetchExerciseId(): Int

    fun setIsMeasuring(isMeasuring: Boolean)

    fun isMeasuring(): Boolean
}