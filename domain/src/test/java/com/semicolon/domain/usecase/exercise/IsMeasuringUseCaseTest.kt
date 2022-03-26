package com.semicolon.domain.usecase.exercise

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.domain.repository.ExerciseRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class IsMeasuringUseCaseTest {

    private val exerciseRepository = mock<ExerciseRepository>()
    private val isMeasuring = MeasuringState.ONGOING

    private val isMeasuringUseCase = IsMeasuringUseCase(exerciseRepository)

    @Test
    fun testIsMeasuringUseCase() {
        runBlocking {
            whenever(exerciseRepository.isMeasuring()).thenReturn(MeasuringState.ONGOING)
            assertEquals(isMeasuringUseCase.execute(Unit), isMeasuring)
        }
    }
}