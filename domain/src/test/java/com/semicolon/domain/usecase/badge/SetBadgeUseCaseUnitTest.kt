package com.semicolon.domain.usecase.badge

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.repository.BadgeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SetBadgeUseCaseUnitTest {

    private val badgeRepository = mock<BadgeRepository>()

    private val setBadgeUseCase = SetBadgeUseCase(badgeRepository)

    @Test
    fun testSetBadgeUseCase() {
        val badgeId = 1
        runBlocking {
            val useCaseResult = setBadgeUseCase.execute(badgeId)
            assertEquals(Unit, useCaseResult)

            verify(badgeRepository, times(1)).setBadge(badgeId)
        }
    }
}