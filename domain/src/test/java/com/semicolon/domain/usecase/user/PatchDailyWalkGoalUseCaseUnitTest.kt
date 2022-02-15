package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.PatchDailyWalkGoalParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PatchDailyWalkGoalUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchDailyWalkGoalUseCase = PatchDailyWalkGoalUseCase(userRepository)

    @Test
    fun testPatchDailyWalkGoalUseCase() {

        val patchDailyWalkGoalParam = PatchDailyWalkGoalParam(1000)

        runBlocking {
            val useCaseResult = patchDailyWalkGoalUseCase.execute(patchDailyWalkGoalParam)
            assertEquals(Unit,useCaseResult)
            verify(userRepository, times(1)).patchDailyWalkGoal(patchDailyWalkGoalParam)
        }
    }
}