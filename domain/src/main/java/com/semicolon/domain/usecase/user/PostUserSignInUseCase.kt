package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserSignInEntity
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostUserSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<PostUserSignInParam, Flow<UserSignInEntity>>() {

    override suspend fun execute(data: PostUserSignInParam): Flow<UserSignInEntity> =
        userRepository.postUserSignIn(data)
}