package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.ChallengeRepository
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PostUserSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<PostUserSignInParam, Unit>() {

    override suspend fun execute(data: PostUserSignInParam) {
        userRepository.postUserSignIn(data)
    }

}