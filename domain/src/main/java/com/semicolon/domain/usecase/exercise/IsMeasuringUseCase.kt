package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.enum.MeasuringState
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class IsMeasuringUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, MeasuringState>() {

    override suspend fun execute(data: Unit): MeasuringState =
        exerciseRepository.isMeasuring()
}