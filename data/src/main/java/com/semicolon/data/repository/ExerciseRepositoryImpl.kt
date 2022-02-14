package com.semicolon.data.repository

import com.semicolon.data.background.task.ExerciseBackgroundTask
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.toRequest
import com.semicolon.data.util.toMultipart
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ExerciseRepositoryImpl @Inject constructor(
    private val remoteImagesDataSource: RemoteImagesDataSource,
    private val remoteExerciseDataSource: RemoteExerciseDataSource,
    private val localExerciseDataSource: LocalExerciseDataSource,
    private val exerciseBackgroundTask: ExerciseBackgroundTask
) : ExerciseRepository {

    override suspend fun fetchDailyExerciseRecord(): Flow<DailyExerciseEntity> =
        localExerciseDataSource.fetchDailyExerciseRecordAsFlow()

    override suspend fun startMeasureExercise(startMeasureExerciseParam: StartMeasureExerciseParam) =
        try {
            val result = remoteExerciseDataSource
                .startMeasureExercise(startMeasureExerciseParam.toRequest())
            localExerciseDataSource.startMeasuring(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                result.exerciseId
            )
        } catch (e: Exception) {
            throw e
        }


    override suspend fun finishMeasureExercise(finishMeasureExerciseParam: FinishMeasureExerciseParam) {
        if (isMeasuring()) try {
            val exerciseId = localExerciseDataSource.fetchExerciseId()
            val period = PeriodParam(
                localExerciseDataSource.fetchStartTime(),
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
            )
            val walkRecord = localExerciseDataSource.fetchWalkRecord(period)
            val imageUrl = if (finishMeasureExerciseParam.verifyImage != null) {
                remoteImagesDataSource.postImages(
                    listOf(finishMeasureExerciseParam.verifyImage!!.toMultipart())
                ).imageUrl.first()
            } else ""
            remoteExerciseDataSource.sendLocationRecords(
                exerciseId,
                localExerciseDataSource.fetchLocationRecord(period).toRequest()
            )
            remoteExerciseDataSource.finishMeasureExercise(
                exerciseId,
                FinishMeasureExerciseRequest(
                    walkRecord.walkCount,
                    walkRecord.traveledDistanceAsMeter * 100,
                    walkRecord.burnedKilocalories.toInt(),
                    imageUrl
                )
            )
            localExerciseDataSource.finishMeasuring()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun isMeasuring(): Boolean =
        localExerciseDataSource.isMeasuring()

    override suspend fun startRecordExercise() =
        exerciseBackgroundTask.synchronizeExerciseRecord(1, TimeUnit.HOURS)
<<<<<<< HEAD
=======

    override suspend fun fetchExerciseRecordList(): Flow<List<ExerciseRecordEntity>> =
        flow { emit(remoteExerciseDataSource.fetchExerciseRecordList().toEntityList()) }

    override suspend fun fetchExerciseAnalysisResult(): Flow<ExerciseAnalysisResultEntity> =
        flow { emit(remoteExerciseDataSource.fetchExerciseAnalysisResult().toEntity()) }
>>>>>>> 60_Notice_data
}