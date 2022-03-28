package com.semicolon.domain.usecase.user

import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class CheckClassCodeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, Unit>() {

    override suspend fun execute(data: String): Unit =
        userRepository.checkClassCode(data)
}