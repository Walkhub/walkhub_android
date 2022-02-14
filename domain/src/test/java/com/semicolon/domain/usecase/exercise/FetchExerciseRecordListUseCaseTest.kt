package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchExerciseRecordListUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val exerciseRecordList = mock<List<ExerciseRecordEntity>>()

    private val fetchExerciseRecordListUseCase =
        FetchExerciseRecordListUseCase(exerciseRepository)

    @Test
    fun testFetchExerciseRecordListUseCase() {
        runBlocking {
            whenever(exerciseRepository.fetchExerciseRecordList()).thenReturn(
                flow { emit(exerciseRecordList) }
            )
            fetchExerciseRecordListUseCase.execute(Unit).collect {
                assertEquals(it, exerciseRecordList)
            }
        }
    }
}