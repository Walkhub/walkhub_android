package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.exercise.ExerciseAnalysisResultEntity
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchExerciseAnalysisResultUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val exerciseAnalysisResult = mock<ExerciseAnalysisResultEntity>()

    private val fetchExerciseAnalysisResultUseCase =
        FetchExerciseAnalysisResultUseCase(exerciseRepository)

    @Test
    fun testFetchExerciseAnalysisResultUseCase() {
        runBlocking {
            whenever(exerciseRepository.fetchExerciseAnalysisResult()).thenReturn(
                flow { emit(exerciseAnalysisResult) }
            )
            fetchExerciseAnalysisResultUseCase.execute(Unit).collect {
                assertEquals(it, exerciseAnalysisResult)
            }
        }
    }
}