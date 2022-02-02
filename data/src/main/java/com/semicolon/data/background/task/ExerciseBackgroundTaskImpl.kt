package com.semicolon.data.background.task

import android.content.Context
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.semicolon.data.background.worker.SynchronizeExerciseWorker
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ExerciseBackgroundTaskImpl @Inject constructor(
    @ApplicationContext val context: Context
) : ExerciseBackgroundTask {

    override fun synchronizeExerciseRecord(interval: Long, unit: TimeUnit) {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val request = PeriodicWorkRequestBuilder<SynchronizeExerciseWorker>(interval, unit)
            .setConstraints(constraints)
            .build()
        WorkManager.getInstance(context).enqueue(request)
    }
}