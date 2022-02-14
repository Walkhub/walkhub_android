package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.PatchDailyWalkGoalParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchDailyWalkGoalUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PatchDailyWalkGoalParam, Unit>() {

    override suspend fun execute(data: PatchDailyWalkGoalParam) =
        userRepository.patchDailyWalkGoal(data)
}