package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserProfileEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserProfileUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Int, Flow<UserProfileEntity>>() {

    override suspend fun execute(data: Int): Flow<UserProfileEntity> =
        userRepository.fetchUserProfile(data)
}