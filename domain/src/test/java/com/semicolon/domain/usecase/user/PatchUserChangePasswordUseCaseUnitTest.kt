package com.semicolon.domain.usecase.user

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PatchUserChangePasswordUseCaseUnitTest {

    private val userRepository = mock<UserRepository>()

    private val patchUserChangePasswordUseCase = PatchUserChangePasswordUseCase(userRepository)

    private val patchUserChangePasswordParam = mock<PatchUserChangePasswordParam>()
    @Test
    fun testPatchUserChangePasswordUseCase() {
        runBlocking {
            val patchUserChangePasswordParam =PatchUserChangePasswordParam(
                "account_id",
                "phone_number",
                "auth_code",
                "new_password"
            )
            val useCaseResult = patchUserChangePasswordUseCase.execute(patchUserChangePasswordParam)
            assertEquals(Unit, useCaseResult)
            verify(userRepository, times(1)).patchUserChangePassword(patchUserChangePasswordParam)
        }
    }
}