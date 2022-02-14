package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test

class StartRecordExerciseUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()

    private val startRecordExerciseUseCase =
        StartRecordExerciseUseCase(exerciseRepository)

    @Test
    fun testStartRecordExerciseUseCase() {
        runBlocking {
            startRecordExerciseUseCase.execute(Unit)
            verify(exerciseRepository).startRecordExercise()
        }
    }
}