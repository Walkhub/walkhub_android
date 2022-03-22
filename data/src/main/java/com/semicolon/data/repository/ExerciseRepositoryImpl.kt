package com.semicolon.data.repository

import com.semicolon.data.background.task.ExerciseBackgroundTask
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.toRequest
import com.semicolon.data.remote.response.exercise.toEntity
import com.semicolon.data.remote.response.exercise.toEntityList
import com.semicolon.data.util.toMultipart
import com.semicolon.domain.entity.exercise.*
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
            localExerciseDataSource.setGoal(
                GoalEntity(
                    startMeasureExerciseParam.goal,
                    startMeasureExerciseParam.goalType
                )
            )
            localExerciseDataSource.startMeasuring(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                result.exerciseId
            )
        } catch (e: Exception) {
            throw e
        }

    override suspend fun pauseMeasureExercise() {
        val period = PeriodParam(
            localExerciseDataSource.fetchStartTime(),
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
        )
        val walkRecord = localExerciseDataSource.fetchWalkRecord(period)
        val locationRecord = localExerciseDataSource.fetchLocationRecord(period)
        localExerciseDataSource.pauseMeasuring(
            walkRecord.walkCount,
            walkRecord.traveledDistanceAsMeter,
            walkRecord.burnedKilocalories,
            locationRecord
        )
    }

    override suspend fun resumeMeasureExercise() {
        val exerciseId = localExerciseDataSource.fetchExerciseId()
        localExerciseDataSource.startMeasuring(
            LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
            exerciseId
        )
    }

    override suspend fun finishMeasureExercise(finishMeasureExerciseParam: FinishMeasureExerciseParam) {
        if (isMeasuring() == MeasuringState.ONGOING) try {
            val exerciseId = localExerciseDataSource.fetchExerciseId()
            pauseMeasureExercise()
            val walkRecord = localExerciseDataSource.fetchAccumulatedRecord()
            val locationRecord = localExerciseDataSource.fetchAccumulatedLocationRecord()
            val pausedTime = localExerciseDataSource.fetchPausedTime()
            val imageUrl = if (finishMeasureExerciseParam.verifyImage != null) {
                remoteImagesDataSource.postImages(
                    listOf(finishMeasureExerciseParam.verifyImage!!.toMultipart())
                ).imageUrl.first()
            } else ""
            remoteExerciseDataSource.sendLocationRecords(
                exerciseId,
                locationRecord.toRequest()
            )
            remoteExerciseDataSource.finishMeasureExercise(
                exerciseId,
                FinishMeasureExerciseRequest(
                    walkRecord.walkCount,
                    walkRecord.traveledDistanceAsMeter * 100,
                    walkRecord.burnedKilocalories.toInt(),
                    imageUrl,
                    pausedTime
                )
            )
            localExerciseDataSource.finishMeasuring()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun isMeasuring(): MeasuringState =
        localExerciseDataSource.isMeasuring()

    override suspend fun startRecordExercise() {
        localExerciseDataSource.startRecordExercise()
        exerciseBackgroundTask.synchronizeExerciseRecord(1, TimeUnit.HOURS)
    }

    override suspend fun fetchExerciseRecordList(): Flow<List<ExerciseRecordEntity>> =
        flow { emit(remoteExerciseDataSource.fetchExerciseRecordList().toEntityList()) }

    override suspend fun fetchExerciseAnalysisResult(): Flow<ExerciseAnalysisResultEntity> =
        flow { emit(remoteExerciseDataSource.fetchExerciseAnalysisResult().toEntity()) }

    override suspend fun fetchMeasuredExerciseRecord(): Flow<ExerciseEntity> =
        localExerciseDataSource.fetchMeasuredExerciseRecord()

    override suspend fun fetchMeasuredTime(): Flow<Long> =
        localExerciseDataSource.fetchMeasuredTime()

    override suspend fun fetchCurrentSpeed(): Flow<Float> =
        localExerciseDataSource.fetchCurrentSpeed()

    override suspend fun fetchGoal(): GoalEntity =
        localExerciseDataSource.fetchGoal()

    override suspend fun fetchExercisingUserList(): Flow<List<ExercisingUserEntity>> {
        TODO("Not yet implemented")
    }
}