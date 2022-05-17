package com.semicolon.data.background.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val localExerciseDataSource: LocalExerciseDataSource,
    private val remoteExerciseDataSource: RemoteExerciseDataSource
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker =
        when(workerClassName) {
            SynchronizeExerciseWorker::class.qualifiedName -> {
                SynchronizeExerciseWorker(
                    appContext,
                    workerParameters,
                    localExerciseDataSource,
                    remoteExerciseDataSource
                )
            }
            LocationTrackingWorker::class.qualifiedName -> {
                LocationTrackingWorker(
                    appContext,
                    workerParameters,
                    localExerciseDataSource
                )
            }
            else -> throw IllegalArgumentException()
        }
//        println("qwerty ${SynchronizeExerciseWorker::class.qualifiedName}")
//        println("Asdadsd $workerClassName")
//        println(SynchronizeExerciseWorker::class.qualifiedName == workerClassName)
//        return SynchronizeExerciseWorker(
//            appContext,
//            workerParameters,
//            localExerciseDataSource,
//            remoteExerciseDataSource
//        )

}