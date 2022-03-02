package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.ExerciseEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMeasuredExerciseRecordUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
): UseCase<Unit, Flow<ExerciseEntity>>() {

    override suspend fun execute(data: Unit): Flow<ExerciseEntity> =
        exerciseRepository.fetchMeasuredExerciseRecord()
}