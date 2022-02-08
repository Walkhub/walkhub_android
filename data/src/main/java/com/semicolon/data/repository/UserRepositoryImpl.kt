package com.semicolon.data.repository

import android.content.Context
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.local.pref.SharedPreferencesManager
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.UserReissueResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.remote.response.users.toEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.domain.entity.users.*
import com.semicolon.domain.exception.basic.NoInternetException
import com.semicolon.domain.exception.basic.UnauthorizedException
import com.semicolon.domain.param.user.*
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

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
        saveAccount(postUserSignInParam)
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

    override suspend fun fetchUserOwnBadge(userId: Int): Flow<UserOwnBadgeEntity> =
        OfflineCacheUtil<UserOwnBadgeEntity>()
            .remoteData { remoteUserDateSource.fetchUserOwnBadge(userId).toEntity() }
            .localData { localUserDataSource.fetchUserOwnBadge(userId) }
            .doOnNeedRefresh { localUserDataSource.insertUserOwnBadge(it) }
            .createFlow()

    override suspend fun setBadge(badgeId: Int) =
        remoteUserDateSource.setBadge(badgeId)

    override suspend fun updateProfile(updateProfileParam: UpdateProfileParam) =
        remoteUserDateSource.updateProfile(updateProfileParam.toRequest())

    override suspend fun findUserAccount(phoneNumber: String): Flow<FindUserAccountEntity> =
        flow {
            emit(remoteUserDateSource.findUserAccount(phoneNumber).toEntity())
        }

    override suspend fun patchUserHealth(patchUserHealthParam: PatchUserHealthParam) =
        remoteUserDateSource.patchUserHealth(patchUserHealthParam.toRequest())

    override suspend fun signUpClass(signUpClassParam: SignUpClassParam) =
        remoteUserDateSource.signUpClass(
            signUpClassParam.agencyCode,
            signUpClassParam.grade,
            signUpClassParam.classRoom,
            signUpClassParam.toRequest()
        )

    override suspend fun patchSchool(agencyCode: String) =
        remoteUserDateSource.patchSchool(agencyCode)

    override suspend fun autoLogin(): Flow<UserSignInEntity> =
        flow {
            emit(
                remoteUserDateSource.postUserSignIn(
                    UserSignInRequest(
                        localUserDataSource.fetchId(),
                        localUserDataSource.fetchPw(),
                        localUserDataSource.fetchDeviceToken()
                    )
                ).toEntity()
            )
        }

    suspend fun saveAccount(userSignInParam: PostUserSignInParam) {
        localUserDataSource.setId(userSignInParam.accountId)
        localUserDataSource.setPw(userSignInParam.password)
        localUserDataSource.setDeviceToken(userSignInParam.deviceToken)
    }

    override suspend fun fetchUserProfile(userId: Int): Flow<UserProfileEntity> =
        OfflineCacheUtil<UserProfileEntity>()
            .remoteData { remoteUserDateSource.fetchUserProfile(userId).toEntity() }
            .localData { localUserDataSource.fetchUserProfile(userId) }
            .doOnNeedRefresh { localUserDataSource.insertUserProfile(userId, it) }
            .createFlow()

    fun SignUpClassParam.toRequest() =
        SignUpClassRequest(
            classCode = classCode,
            number = number
        )

    fun PatchUserHealthParam.toRequest() =
        PatchUserHealthRequest(
            height = height,
            weight = weight
        )

    fun UpdateProfileParam.toRequest() =
        UpdateProfileRequest(
            birthday = birthday,
            name = name,
            profileUrl = profileUrl,
            sex = sex
        )

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