package com.semicolon.data.background.task

import java.util.concurrent.TimeUnit

interface ExerciseBackgroundTask {

    fun synchronizeExerciseRecord(interval: Long, unit: TimeUnit)
}