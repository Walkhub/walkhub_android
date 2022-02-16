package com.semicolon.domain.usecase.badge

import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMyBadgesUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) : UseCase<Unit, Flow<FetchMyBadgesEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchMyBadgesEntity> =
        badgeRepository.fetchMyBadges()
}