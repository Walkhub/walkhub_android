package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCurrentSpeedUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
): UseCase<Unit, Flow<Float>>() {

    override suspend fun execute(data: Unit): Flow<Float> =
        exerciseRepository.fetchCurrentSpeed()
}