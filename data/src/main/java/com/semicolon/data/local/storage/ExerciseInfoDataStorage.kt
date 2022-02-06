package com.semicolon.data.local.storage

interface ExerciseInfoDataStorage {

    fun setStartTime(timeAsMilli: Long)

    fun fetchStartTime(): Long

    fun setExerciseId(id: Int)

    fun fetchExerciseId(): Int

    fun setIsMeasuring(isMeasuring: Boolean)

    fun isMeasuring(): Boolean
}