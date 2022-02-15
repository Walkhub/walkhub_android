package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PatchSchoolUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchSchoolUseCase= PatchSchoolUseCase(userRepository)

    @Test
    fun testPatchSchoolUseCase() {
        runBlocking {
            val useCaseResult = patchSchoolUseCase.execute(any())
            assertEquals(Unit,useCaseResult)
            verify(userRepository, times(1)).patchSchool(any())
        }
    }
}