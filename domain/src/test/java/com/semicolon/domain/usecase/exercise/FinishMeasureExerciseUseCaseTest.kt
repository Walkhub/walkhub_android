package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class FinishMeasureExerciseUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val finishMeasureExerciseParam = mock<FinishMeasureExerciseParam>()

    private val finishMeasureExerciseUseCase =
        FinishMeasureExerciseUseCase(exerciseRepository)

    @Test
    fun testFinishMeasureExerciseUseCase() {
        runBlocking {
            finishMeasureExerciseUseCase.execute(finishMeasureExerciseParam)
            verify(exerciseRepository).finishMeasureExercise(finishMeasureExerciseParam)
        }
    }
}