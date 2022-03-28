package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.background.task.ExerciseBackgroundTask
import com.semicolon.data.local.datasource.LocalExerciseDataSource
import com.semicolon.data.local.entity.exercise.LocationRecordEntity
import com.semicolon.data.local.entity.exercise.WalkRecordEntity
import com.semicolon.data.local.param.PeriodParam
import com.semicolon.data.remote.datasource.RemoteExerciseDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.request.exercise.FinishMeasureExerciseRequest
import com.semicolon.data.remote.request.exercise.toRequest
import com.semicolon.data.remote.response.exercise.*
import com.semicolon.data.remote.response.image.ImagesResponse
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.enums.GoalType
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.io.File
import java.util.concurrent.TimeUnit

class ExerciseRepositoryUnitTest {

    private val remoteImagesDataSource = mock<RemoteImagesDataSource>()
    private val remoteExerciseDataSource = mock<RemoteExerciseDataSource>()
    private val localExerciseDataSource = mock<LocalExerciseDataSource>()
    private val exerciseBackgroundTask = mock<ExerciseBackgroundTask>()

    private val dailyExercise = mock<DailyExerciseEntity>()
    private val exerciseIdResponse = mock<ExerciseIdResponse>()
    private val file = mock<File>()
    private val periodParam = mock<PeriodParam>()
    private val walkRecord = mock<WalkRecordEntity>()
    private val locationRecord = mock<List<LocationRecordEntity>>()
    private val exerciseRecordList = mock<ExerciseRecordListResponse>()
    private val exerciseAnalysisResult = mock<ExerciseAnalysisResultResponse>()

    private val exerciseRepository = ExerciseRepositoryImpl(
        remoteImagesDataSource,
        remoteExerciseDataSource,
        localExerciseDataSource,
        exerciseBackgroundTask
    )

    @Test
    fun testFetchDailyExerciseRecord() {
        runBlocking {
            whenever(localExerciseDataSource.fetchDailyExerciseRecordAsFlow())
                .thenReturn(flow { emit(dailyExercise) })
            exerciseRepository.fetchDailyExerciseRecord()
                .collect { assertEquals(it, dailyExercise) }
        }
    }

    @Test
    fun testStartMeasureExercise() {
        val startMeasureExerciseParam =
            StartMeasureExerciseParam(0, GoalType.WALK_COUNT)

        runBlocking {
            whenever(remoteExerciseDataSource.startMeasureExercise(startMeasureExerciseParam.toRequest()))
                .thenReturn(exerciseIdResponse)
            exerciseRepository.startMeasureExercise(startMeasureExerciseParam)
            verify(localExerciseDataSource).startMeasuring(
                LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond(),
                exerciseIdResponse.exerciseId
            )
        }
    }

/*    @Test
    fun testFinishMeasureExercise() {
        val finishMeasureExerciseParam = FinishMeasureExerciseParam(File("string"))
        val imageUrl = "https://test.com/test.png"
        val imageResponse = ImagesResponse(listOf(imageUrl))
        val locationRecord = listOf(LocationRecordEntity(1.0, 1.0))

        runBlocking {
            whenever(localExerciseDataSource.isMeasuring())
                .thenReturn(MeasuringState.ONGOING)
            whenever(localExerciseDataSource.fetchStartTime())
                .thenReturn(0)
            whenever(localExerciseDataSource.fetchExerciseId())
                .thenReturn(1)
            whenever(localExerciseDataSource.fetchWalkRecord(any()))
                .thenReturn(walkRecord)
            whenever(remoteImagesDataSource.postImages(any()))
                .thenReturn(imageResponse)
            whenever(localExerciseDataSource.fetchLocationRecord(any()))
                .thenReturn(locationRecord)

            exerciseRepository.finishMeasureExercise(finishMeasureExerciseParam)
            verify(remoteExerciseDataSource)
                .sendLocationRecords(1, locationRecord.toRequest())
            verify(remoteExerciseDataSource)
                .finishMeasureExercise(
                    1,
                    FinishMeasureExerciseRequest(
                        walkRecord.walkCount,
                        walkRecord.traveledDistanceAsMeter * 100,
                        walkRecord.burnedKilocalories.toInt(),
                        imageUrl,
                        100000
                    )
                )
            verify(localExerciseDataSource).finishMeasuring()
        }
    }*/

    @Test
    fun testIsMeasuring() {
        runBlocking {
            whenever(localExerciseDataSource.isMeasuring()).thenReturn(MeasuringState.ONGOING)
            assertEquals(exerciseRepository.isMeasuring(), MeasuringState.ONGOING)
        }
    }

    @Test
    fun testStartRecordExercise() {
        runBlocking {
            exerciseRepository.startRecordExercise()
            verify(exerciseBackgroundTask).synchronizeExerciseRecord(1, TimeUnit.HOURS)
        }
    }

    @Test
    fun testFetchExerciseRecordList() {
        runBlocking {
            whenever(remoteExerciseDataSource.fetchExerciseRecordList())
                .thenReturn(exerciseRecordList)
            exerciseRepository.fetchExerciseRecordList()
                .collect { assertEquals(it, exerciseRecordList.toEntityList()) }
        }
    }

    @Test
    fun testFetchExerciseAnalysisResult() {
        runBlocking {
            whenever(remoteExerciseDataSource.fetchExerciseAnalysisResult())
                .thenReturn(exerciseAnalysisResult)
            exerciseRepository.fetchExerciseAnalysisResult()
                .collect { assertEquals(it, exerciseAnalysisResult.toEntity()) }
        }
    }
}