package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteChallengeDateSource: RemoteUserDataSource
) : UserRepository {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ) = remoteChallengeDateSource.verifyUserPhoneNumber(verifyPhoneNumberSignUpParam.toRequest())

    override suspend fun postUserSignUp(
        postUserSignUpParam: PostUserSignUpParam
    ) = remoteChallengeDateSource.postUserSignUp(postUserSignUpParam.toRequest())

    override suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    ): Flow<UserSignInResponse> =
        flow {
            emit(remoteChallengeDateSource.postUserSignIn(postUserSignInParam.toRequest()))
        }


    fun VerifyPhoneNumberSignUpParam.toRequest() =
        VerifyPhoneNumberSignUpRequest(
            phoneNumber = phoneNumber
        )

    fun PostUserSignUpParam.toRequest() =
        UserSignUpRequest(
            accountId = accountId,
            password = password,
            name = name,
            phoneNumber = phoneNumber,
            authCode = authCode
        )

    fun PostUserSignInParam.toRequest() =
        UserSignInRequest(
            accountId = accountId,
            password = password,
            deviceToken = deviceToken
        )
}