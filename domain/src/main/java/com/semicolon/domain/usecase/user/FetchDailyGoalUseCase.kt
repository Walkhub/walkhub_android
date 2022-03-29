package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.FetchDailyGoalEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchDailyGoalUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Flow<FetchDailyGoalEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchDailyGoalEntity> =
        userRepository.fetchDailyGoal()
}