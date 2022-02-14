package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase

class StartMeasureExerciseUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<StartMeasureExerciseParam, Unit>() {

    override suspend fun execute(data: StartMeasureExerciseParam) =
        exerciseRepository.startMeasureExercise(data)
}