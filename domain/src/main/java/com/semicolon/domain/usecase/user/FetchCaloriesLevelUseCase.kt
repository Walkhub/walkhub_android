package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchCaloriesLevelUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchCaloriesLevelEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchCaloriesLevelEntity> =
        userRepository.fetchCaloriesLevel()
}