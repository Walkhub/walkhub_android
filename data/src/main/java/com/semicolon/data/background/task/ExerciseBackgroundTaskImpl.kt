package com.semicolon.data.background.task

import android.content.Context
import androidx.work.*
import com.semicolon.data.background.worker.LocationTrackingWorker
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

    override fun startRecordLocation() {
        val request = OneTimeWorkRequestBuilder<LocationTrackingWorker>()
            .addTag(LOCATION_TRACKING)
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .build()
        WorkManager.getInstance(context).enqueue(request)
    }

    override fun stopRecordLocation() {
        WorkManager.getInstance(context)
            .cancelAllWorkByTag(LOCATION_TRACKING)
    }

    companion object {
        const val LOCATION_TRACKING = "LOCATION_TRACKING"
    }
}