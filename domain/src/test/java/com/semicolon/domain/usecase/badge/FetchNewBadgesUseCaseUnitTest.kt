package com.semicolon.domain.usecase.badge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.repository.BadgeRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchNewBadgesUseCaseUnitTest {

    private val badgeRepository = mock<BadgeRepository>()

    private val fetchNewBadgesUseCase = FetchNewBadgesUseCase(badgeRepository)

    private val fetchNewBadgesEntity = mock<FetchNewBadgesEntity>()

    @Test
    fun testFetchNewBadgeUseCase() {

        runBlocking {
            whenever(badgeRepository.fetchNewBadges()).thenReturn(
                flow { emit(fetchNewBadgesEntity) }
            )

            fetchNewBadgesUseCase.execute(Unit).collect {
                assertEquals(fetchNewBadgesEntity, it)
            }

            verify(badgeRepository, times(1)).fetchNewBadges()
        }
    }
}