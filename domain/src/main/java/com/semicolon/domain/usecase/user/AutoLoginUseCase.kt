package com.semicolon.domain.usecase.user

import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AutoLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit): Unit =
        userRepository.autoLogin()
}