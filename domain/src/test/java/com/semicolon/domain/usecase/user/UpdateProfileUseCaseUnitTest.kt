package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.UpdateProfileParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class UpdateProfileUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val updateProfileUseCase = UpdateProfileUseCase(userRepository)

    private val updateProfileParam = mock<UpdateProfileParam>()

    @Test
    fun testUpDateProfileUseCase() {

        runBlocking {
            val useCaseResult = updateProfileUseCase.execute(updateProfileParam)
            assertEquals(Unit,useCaseResult)

            verify(userRepository, times(1)).updateProfile(updateProfileParam)
        }
    }
}