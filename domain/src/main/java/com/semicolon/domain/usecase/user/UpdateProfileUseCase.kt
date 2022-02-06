package com.semicolon.domain.usecase.user

import com.semicolon.domain.param.user.UpdateProfileParam
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<UpdateProfileParam, Unit>() {

    override suspend fun execute(data: UpdateProfileParam) =
        userRepository.updateProfile(data)

}