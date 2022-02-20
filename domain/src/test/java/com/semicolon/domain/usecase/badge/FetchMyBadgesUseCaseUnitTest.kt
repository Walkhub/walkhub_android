package com.semicolon.domain.usecase.badge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchMyBadgesUseCaseUnitTest {

    private val badgeRepository = mock<BadgeRepository>()

    private val fetchMyBadgesUseCase = FetchMyBadgesUseCase(badgeRepository)

    private val fetchMyBadgesEntity = mock<FetchMyBadgesEntity>()

    @Test
    fun testFetchMyBadgeUseCase() {

        runBlocking {
            whenever(badgeRepository.fetchMyBadges()).thenReturn(
                flow { emit(fetchMyBadgesEntity) }
            )

            fetchMyBadgesUseCase.execute(Unit).collect {
                assertEquals(fetchMyBadgesEntity, it)
            }

            verify(badgeRepository, times(1)).fetchMyBadges()
        }
    }
}