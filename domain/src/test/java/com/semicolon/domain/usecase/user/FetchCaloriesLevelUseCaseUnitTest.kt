package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.*
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchCaloriesLevelUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val fetchCaloriesLevelUseCase = FetchCaloriesLevelUseCase(userRepository)

    private val fetchCaloriesLevelEntity = mock<FetchCaloriesLevelEntity>()

    @Test
    fun testFetchCaloriesLevelUseCase() {

        runBlocking {
            whenever(userRepository.fetchCaloriesLevel()).thenReturn(
                flow { emit(fetchCaloriesLevelEntity) }
            )

            fetchCaloriesLevelUseCase.execute(Unit).collect {
                assertEquals(fetchCaloriesLevelEntity, it)
            }
            verify(userRepository, times(1)).fetchCaloriesLevel()
        }
    }
}