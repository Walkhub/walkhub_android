package com.semicolon.data.background.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import com.semicolon.data.remote.request.exercise.SaveDailyExerciseRequest
import kotlinx.coroutines.flow.first
import org.threeten.bp.LocalDate
import java.lang.Exception
import javax.inject.Inject

class SynchronizeExerciseWorker(
    context: Context, params: WorkerParameters
) : CoroutineWorker(context, params) {

    @Inject
    lateinit var localExerciseDataSource: LocalExerciseDataSource

    @Inject
    lateinit var remoteExerciseDataSource: RemoteExerciseDataSource

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