package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.domain.entity.users.UserSignInEntity

interface RemoteUserDataSource {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )
}