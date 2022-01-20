package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.users.UserSignInEntity
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
