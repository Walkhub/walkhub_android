package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class VerifyUserPhoneNumberUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<VerifyPhoneNumberSignUpParam, Unit>() {

    override suspend fun execute(data: VerifyPhoneNumberSignUpParam) {
        userRepository.verifyUserPhoneNumber(data)
    }

}