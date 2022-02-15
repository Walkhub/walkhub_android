package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FindUserAccountUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val findUserAccountUseCase = FindUserAccountUseCase(userRepository)

    private val findUserAccountEntity = mock<FindUserAccountEntity>()

    @Test
    fun testFindUserAccountUseCase() {
        val phoneNumber ="010-1234-2345"
        runBlocking {
            whenever(userRepository.findUserAccount(any())).thenReturn(
                flow { emit(findUserAccountEntity) }
            )

            findUserAccountUseCase.execute(phoneNumber).collect{
                assertEquals(findUserAccountEntity,it)
            }
        }
    }
}