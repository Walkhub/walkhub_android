package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.FinishExerciseMeasureParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase

class FinishMeasureExerciseUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<FinishExerciseMeasureParam, Unit>() {

    override suspend fun execute(data: FinishExerciseMeasureParam) =
        exerciseRepository.finishMeasureExercise(data)
}