package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class PostUserSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PostUserSignUpParam, Unit>() {

    override suspend fun execute(data: PostUserSignUpParam) {
        userRepository.postUserSignUp(data)
    }

}