package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class IsMeasuringUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Boolean>() {

    override suspend fun execute(data: Unit): Boolean =
        exerciseRepository.isMeasuring()
}