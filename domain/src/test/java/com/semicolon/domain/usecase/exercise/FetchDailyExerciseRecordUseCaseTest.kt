package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchDailyExerciseRecordUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val mockDailyExercise = mock<DailyExerciseEntity>()

    private val fetchDailyExerciseRecordUseCase =
        FetchDailyExerciseRecordUseCase(exerciseRepository)

    @Test
    fun testFetchDailyExerciseRecordUseCase() {
        runBlocking {
            whenever(exerciseRepository.fetchDailyExerciseRecord()).thenReturn(
                flow { emit(mockDailyExercise) }
            )
            fetchDailyExerciseRecordUseCase.execute(Unit).collect {
                assertEquals(it, mockDailyExercise)
            }
        }
    }
}