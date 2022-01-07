package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.StartExerciseMeasureParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase

class StartExerciseMeasureUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<StartExerciseMeasureParam, Unit>() {

    override suspend fun execute(data: StartExerciseMeasureParam) =
        exerciseRepository.startExerciseMeasure(data)
}