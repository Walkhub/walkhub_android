package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StartMeasureExerciseUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val startMeasureExerciseParam = mock<StartMeasureExerciseParam>()

    private val startMeasureExerciseUseCase =
        StartMeasureExerciseUseCase(exerciseRepository)

    @Test
    fun testStartMeasureExerciseUseCase() {
        runBlocking {
            startMeasureExerciseUseCase.execute(startMeasureExerciseParam)
            verify(exerciseRepository).startMeasureExercise(startMeasureExerciseParam)
        }
    }
}