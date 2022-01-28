package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.local.entity.user.toDbEntity
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.UserChangePasswordRequest
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.users.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.challenge.ChallengeDetailEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
import com.semicolon.domain.entity.users.UserSignInEntity
import com.semicolon.domain.param.user.PatchUserChangePasswordParam
import com.semicolon.domain.param.user.PostUserSignInParam
import com.semicolon.domain.param.user.PostUserSignUpParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDateSource: RemoteUserDataSource
) : UserRepository {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ) = remoteUserDateSource.verifyUserPhoneNumber(verifyPhoneNumberSignUpParam.toRequest())

    override suspend fun postUserSignUp(
        postUserSignUpParam: PostUserSignUpParam
    ) = remoteUserDateSource.postUserSignUp(postUserSignUpParam.toRequest())

    override suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    ): Flow<UserSignInEntity> = flow {
        emit(remoteUserDateSource.postUserSignIn(postUserSignInParam.toRequest()).toEntity())
    }

    override suspend fun patchUserChangePassword(
        patchUserChangePasswordParam: PatchUserChangePasswordParam
    ) = remoteUserDateSource.patchUserChangePassword(patchUserChangePasswordParam.toRequest())

    override suspend fun fetchMyPage(): Flow<UserMyPageEntity> =
        OfflineCacheUtil<UserMyPageEntity>()
            .remoteData { remoteUserDateSource.fetchMyPage().toEntity() }
            .localData { localUserDataSource.fetchUserMyPage() }
            .doOnNeedRefresh { localUserDataSource.insertUserMyPage(it) }
            .createFlow()

    override suspend fun fetchUserProfile(userId: Int): Flow<UserProfileEntity> {
        TODO("Not yet implemented")
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

    fun PatchUserChangePasswordParam.toRequest() =
        UserChangePasswordRequest(
            accountId = accountId,
            phoneNumber = phoneNumber,
            authCode = authCode,
            newPassword = newPassword
        )
}