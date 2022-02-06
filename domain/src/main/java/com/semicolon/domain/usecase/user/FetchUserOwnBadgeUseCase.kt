package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserOwnBadgeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Int, Flow<UserOwnBadgeEntity>>() {

    override suspend fun execute(userId: Int): Flow<UserOwnBadgeEntity> =
        userRepository.fetchUserOwnBadge(userId)
}