package com.semicolon.domain.usecase.user

import com.semicolon.domain.entity.users.UserOwnBadgeEntity
import com.semicolon.domain.repository.UserRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SetBadgeUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<Int, Unit>() {

    override suspend fun execute(badgeId: Int) =
        userRepository.setBadge(badgeId)

}