package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase

class StartRecordExerciseUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) =
        exerciseRepository.startRecordExercise()
}