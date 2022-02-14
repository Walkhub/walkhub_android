package com.semicolon.domain.usecase.badge

import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserBadgesUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) : UseCase<Int, Flow<FetchUserBadgesEntity>>() {

    override suspend fun execute(userId: Int): Flow<FetchUserBadgesEntity> =
        badgeRepository.fetchUserBadges(userId)
}