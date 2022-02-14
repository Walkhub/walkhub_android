package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.DailyExerciseEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow

class FetchDailyExerciseRecordUseCase(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Flow<DailyExerciseEntity>>() {

    override suspend fun execute(data: Unit): Flow<DailyExerciseEntity> =
        exerciseRepository.fetchDailyExerciseRecord()
}