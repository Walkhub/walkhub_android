package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.VerifyPasswordParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class VerifyPasswordUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<VerifyPasswordParam, Unit>() {

    override suspend fun execute(data: VerifyPasswordParam) =
        userRepository.verifyPassword(data)
}