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

    private val schoolId = 2

    @Test
    fun testPatchSchoolUseCase() {
        runBlocking {
            val useCaseResult = patchSchoolUseCase.execute(schoolId)
            assertEquals(Unit,useCaseResult)

            verify(userRepository, times(1)).patchSchool(schoolId)
        }
    }
}