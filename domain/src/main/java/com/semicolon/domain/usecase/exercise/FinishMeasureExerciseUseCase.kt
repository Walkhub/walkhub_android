package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.FinishMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class FinishMeasureExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<FinishMeasureExerciseParam, Unit>() {

    override suspend fun execute(data: FinishMeasureExerciseParam) =
        exerciseRepository.finishMeasureExercise(data)
}