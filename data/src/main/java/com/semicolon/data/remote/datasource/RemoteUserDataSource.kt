package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest

interface RemoteUserDataSource {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )
}