package com.semicolon.data.background.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.first
import org.threeten.bp.LocalDate
import java.lang.Exception

@HiltWorker
class SynchronizeExerciseWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val localExerciseDataSource: LocalExerciseDataSource,
    private val remoteExerciseDataSource: RemoteExerciseDataSource
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result =
        try {
            val exerciseRecord = localExerciseDataSource.fetchDailyExerciseRecordAsFlow().first()
            val date = LocalDate.now().toString()
            remoteExerciseDataSource.saveDailyExercise(
                date,
                SaveDailyExerciseRequest(
                    distanceAsCentimeter = exerciseRecord.traveledDistanceAsMeter * 100,
                    walkCount = exerciseRecord.stepCount
                )
            )
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
}