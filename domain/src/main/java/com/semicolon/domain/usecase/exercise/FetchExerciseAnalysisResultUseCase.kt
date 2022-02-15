package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.ExerciseAnalysisResultEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchExerciseAnalysisResultUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Flow<ExerciseAnalysisResultEntity>>() {

    override suspend fun execute(data: Unit): Flow<ExerciseAnalysisResultEntity> =
        exerciseRepository.fetchExerciseAnalysisResult()
}