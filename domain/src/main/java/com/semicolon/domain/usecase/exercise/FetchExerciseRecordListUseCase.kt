package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.ExerciseRecordEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchExerciseRecordListUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Flow<List<ExerciseRecordEntity>>>() {

    override suspend fun execute(data: Unit): Flow<List<ExerciseRecordEntity>> =
        exerciseRepository.fetchExerciseRecordList()
}