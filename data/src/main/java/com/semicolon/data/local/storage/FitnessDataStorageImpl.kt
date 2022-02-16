package com.semicolon.data.local.storage

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.fitness.result.DataReadResponse
import com.google.android.gms.tasks.Task
import com.semicolon.data.local.param.PeriodParam
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.concurrent.TimeUnit

class FitnessDataStorageImpl(
    @ApplicationContext private val context: Context
) : FitnessDataStorage {

    private val fitnessOptions: FitnessOptions by lazy {
        FitnessOptions.builder()
            .addDataType(DataType.AGGREGATE_STEP_COUNT_DELTA)
            .addDataType(DataType.AGGREGATE_MOVE_MINUTES)
            .addDataType(DataType.AGGREGATE_DISTANCE_DELTA)
            .addDataType(DataType.AGGREGATE_CALORIES_EXPENDED)
            .addDataType(DataType.TYPE_LOCATION_SAMPLE)
            .build()
    }

    override suspend fun fetchExerciseRecord(periodParam: PeriodParam): Task<DataReadResponse> =
        Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(
                DataReadRequest.Builder()
                    .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                    .aggregate(DataType.AGGREGATE_MOVE_MINUTES)
                    .aggregate(DataType.AGGREGATE_DISTANCE_DELTA)
                    .aggregate(DataType.AGGREGATE_CALORIES_EXPENDED)
                    .setTimeRange(
                        periodParam.startTimeAsSecond,
                        periodParam.endTimeAsSecond,
                        TimeUnit.SECONDS
                    )
                    .bucketByTime(1, TimeUnit.DAYS)
                    .build()
            )

    override suspend fun fetchLocationRecord(periodParam: PeriodParam): Task<DataReadResponse> =
        Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(
                DataReadRequest.Builder()
                    .aggregate(DataType.TYPE_LOCATION_SAMPLE)
                    .setTimeRange(
                        periodParam.startTimeAsSecond,
                        periodParam.endTimeAsSecond,
                        TimeUnit.SECONDS
                    )
                    .bucketByTime(5, TimeUnit.MINUTES)
                    .build()
            )

    override suspend fun fetchWalkRecord(periodParam: PeriodParam): Task<DataReadResponse> =
        Fitness.getHistoryClient(context, getGoogleAccount())
            .readData(
                DataReadRequest.Builder()
                    .aggregate(DataType.AGGREGATE_STEP_COUNT_DELTA)
                    .aggregate(DataType.AGGREGATE_DISTANCE_DELTA)
                    .setTimeRange(
                        periodParam.startTimeAsSecond,
                        periodParam.endTimeAsSecond,
                        TimeUnit.SECONDS
                    )
                    .bucketByTime(1, TimeUnit.DAYS)
                    .build()
            )

    override suspend fun startRecordExercise(
        onSuccess: (DataType) -> Unit,
        onFailure: (DataType) -> Unit
    ) {
        val stepCount = DataType.AGGREGATE_STEP_COUNT_DELTA
        val moveMinute = DataType.AGGREGATE_MOVE_MINUTES
        val distance = DataType.AGGREGATE_DISTANCE_DELTA
        val calories = DataType.AGGREGATE_CALORIES_EXPENDED
        val location = DataType.TYPE_LOCATION_SAMPLE

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(stepCount)
            .addOnSuccessListener { onSuccess(stepCount) }
            .addOnFailureListener { onFailure(stepCount) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(moveMinute)
            .addOnSuccessListener { onSuccess(moveMinute) }
            .addOnFailureListener { onFailure(moveMinute) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(distance)
            .addOnSuccessListener { onSuccess(distance) }
            .addOnFailureListener { onFailure(distance) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(calories)
            .addOnSuccessListener { onSuccess(calories) }
            .addOnFailureListener { onFailure(calories) }

        Fitness.getRecordingClient(context, getGoogleAccount()).subscribe(location)
            .addOnSuccessListener { onSuccess(location) }
            .addOnFailureListener { onFailure(location) }
    }


    private fun getGoogleAccount() =
        GoogleSignIn.getAccountForExtension(context, fitnessOptions)
}