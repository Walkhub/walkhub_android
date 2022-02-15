package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PatchSchoolUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchSchoolUseCase= PatchSchoolUseCase(userRepository)

    @Test
    fun testPatchSchoolUseCase() {
        val schoolId = 1
        runBlocking {
            val useCaseResult = patchSchoolUseCase.execute(schoolId)
            assertEquals(Unit,useCaseResult)
        }
    }
}