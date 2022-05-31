package com.semicolon.data.background.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import javax.inject.Inject

class SynchronizeExerciseWorkerFactory @Inject constructor(
    private val localExerciseDataSource: LocalExerciseDataSource,
    private val remoteExerciseDataSource: RemoteExerciseDataSource
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
        SynchronizeExerciseWorker(
            appContext,
            workerParameters,
            localExerciseDataSource,
            remoteExerciseDataSource
        )
}