package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.param.exercise.StartMeasureExerciseParam
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class StartMeasureExerciseUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<StartMeasureExerciseParam, Unit>() {

    override suspend fun execute(data: StartMeasureExerciseParam) =
        exerciseRepository.startMeasureExercise(data)
}