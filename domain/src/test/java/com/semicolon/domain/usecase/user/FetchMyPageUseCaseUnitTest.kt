package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchMyPageUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val fetchMyPageUseCase = FetchMypageUseCase(userRepository)

    private val userMyPageEntity = mock<UserMyPageEntity>()

    @Test
    fun testFetchMyPageUseCase() {

        runBlocking {
            whenever(userRepository.fetchMyPage()).thenReturn(
                flow { emit(userMyPageEntity) }
            )

            fetchMyPageUseCase.execute(Unit).collect {
                assertEquals(userMyPageEntity, it)
            }

            verify(userRepository, times(1)).fetchMyPage()
        }
    }
}