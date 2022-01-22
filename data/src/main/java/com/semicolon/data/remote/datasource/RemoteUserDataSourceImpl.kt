package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : RemoteUserDataSource {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.verifyPhoneNumberSignUp(verifyPhoneNumberSignUpRequest) }
        .sendRequest()

    override suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.userSignUp(userSignUpRequest) }
        .sendRequest()
}
