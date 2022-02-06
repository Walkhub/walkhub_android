package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.param.user.SignUpClassParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignUpClassUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<SignUpClassParam, Unit>() {

    override suspend fun execute(data: SignUpClassParam) =
        userRepository.signUpClass(data)

}