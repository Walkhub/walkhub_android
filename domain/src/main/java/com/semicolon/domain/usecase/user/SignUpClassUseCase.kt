package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.SignUpClassParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class SignUpClassUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<SignUpClassParam, Unit>() {

    override suspend fun execute(data: SignUpClassParam) =
        userRepository.signUpClass(data)
}