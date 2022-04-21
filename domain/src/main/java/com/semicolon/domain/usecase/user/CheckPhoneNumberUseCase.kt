package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class CheckPhoneNumberUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<CheckPhoneNumberParam, Unit>() {

    override suspend fun execute(data: CheckPhoneNumberParam) =
        userRepository.checkPhoneNumber(data)
}