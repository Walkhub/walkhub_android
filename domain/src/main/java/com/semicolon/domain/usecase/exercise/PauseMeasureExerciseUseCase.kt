package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PauseMeasureExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
): UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) =
        exerciseRepository.pauseMeasureExercise()
}