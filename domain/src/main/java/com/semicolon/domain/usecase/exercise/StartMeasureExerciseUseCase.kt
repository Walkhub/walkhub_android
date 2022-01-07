package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.StartExerciseMeasureParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase

class StartMeasureExerciseUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<StartExerciseMeasureParam, Unit>() {

    override suspend fun execute(data: StartExerciseMeasureParam) =
        exerciseRepository.startMeasureExercise(data)
}