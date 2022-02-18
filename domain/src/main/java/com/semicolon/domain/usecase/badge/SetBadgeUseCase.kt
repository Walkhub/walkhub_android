package com.semicolon.domain.usecase.badge

import com.semicolon.domain.repository.BadgeRepository
import com.semicolon.domain.usecase.UseCase
import javax.inject.Inject

class SetBadgeUseCase @Inject constructor(
    private val badgeRepository: BadgeRepository
): UseCase<Int, Unit>() {

    override suspend fun execute(data: Int) =
        badgeRepository.setBadge(data)
}