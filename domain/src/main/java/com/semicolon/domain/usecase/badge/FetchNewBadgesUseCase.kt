package com.semicolon.domain.usecase.badge

import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchNewBadgesUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
) : UseCase<Unit, Flow<FetchNewBadgesEntity>>() {

    override suspend fun execute(data: Unit): Flow<FetchNewBadgesEntity> =
        badgeRepository.fetchNewBadges()
}