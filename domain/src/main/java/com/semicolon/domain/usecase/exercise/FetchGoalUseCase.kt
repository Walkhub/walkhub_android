package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.GoalEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class FetchGoalUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
): UseCase<Unit, GoalEntity>() {

    override suspend fun execute(data: Unit): GoalEntity =
        exerciseRepository.fetchGoal()
}