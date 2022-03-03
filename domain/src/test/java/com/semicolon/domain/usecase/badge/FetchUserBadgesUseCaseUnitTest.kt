package com.semicolon.domain.usecase.badge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchUserBadgesUseCaseUnitTest {

    private val badgeRepository = mock<BadgeRepository>()

    private val fetchUserBadgesUseCase = FetchUserBadgesUseCase(badgeRepository)

    private val fetchUserBadgesEntity = mock<FetchUserBadgesEntity>()

    @Test
    fun testFetchUserBadgesUseCase() {
        val userId = 1
        runBlocking {
            whenever(badgeRepository.fetchUserBadges(userId)).thenReturn(
                flow { emit(fetchUserBadgesEntity) }
            )

            fetchUserBadgesUseCase.execute(userId).collect {
                assertEquals(fetchUserBadgesEntity, it)
            }

            verify(badgeRepository, times(1)).fetchUserBadges(userId)
        }
    }
}