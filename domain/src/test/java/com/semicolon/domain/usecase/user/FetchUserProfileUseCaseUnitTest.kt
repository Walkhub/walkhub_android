package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.users.UserProfileEntity
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchUserProfileUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val fetchUserProfileUseCase = FetchUserProfileUseCase(userRepository)

    private val userProfileEntity = mock<UserProfileEntity>()

    @Test
    fun testFetchUserProfileUseCase() {
        val userId = 2
        runBlocking {
            whenever(userRepository.fetchUserProfile(userId)).thenReturn(
                flow { emit(userProfileEntity) }
            )
            fetchUserProfileUseCase.execute(userId).collect{
                assertEquals(userProfileEntity,it)
            }
            verify(userRepository,times(1)).fetchUserProfile(userId)
        }
    }
}