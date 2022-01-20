package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class FetchMypageUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Unit, Unit>() {

    override suspend fun execute(data: Unit) {
        userRepository.fetchMypage()
    }

}