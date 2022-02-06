package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PatchUserHealthUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PatchUserHealthParam, Unit>() {

    override suspend fun execute(data: PatchUserHealthParam) =
        userRepository.patchUserHealth(data)

}