package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest

interface RemoteUserDataSource {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )
}