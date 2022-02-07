package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.PatchUserHealthParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PatchUserHealthUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PatchUserHealthParam, Unit>() {

    override suspend fun execute(data: PatchUserHealthParam) =
        userRepository.patchUserHealth(data)
}