package com.semicolon.domain.usecase.exercise

import com.semicolon.domain.entity.exercise.ExercisingUserEntity
import com.semicolon.domain.repository.ExerciseRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchExercisingUserListUseCase @Inject constructor(
    private val exerciseRepository: ExerciseRepository
) : UseCase<Unit, Flow<List<ExercisingUserEntity>>>() {

    override suspend fun execute(data: Unit): Flow<List<ExercisingUserEntity>> =
        exerciseRepository.fetchExercisingUserList()
}