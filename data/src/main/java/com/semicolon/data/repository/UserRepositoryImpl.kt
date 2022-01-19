package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteChallengeDateSource: RemoteUserDataSource
) : UserRepository {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ) = remoteChallengeDateSource.verifyUserPhoneNumber(verifyPhoneNumberSignUpParam.toRequest())


    fun VerifyPhoneNumberSignUpParam.toRequest() =
        VerifyPhoneNumberSignUpRequest(
            phoneNumber = phoneNumber
        )
}