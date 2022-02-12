package com.semicolon.data.repository

import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.remote.response.users.toEntity
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.data.util.toMultipart
import com.semicolon.domain.entity.users.*
import com.semicolon.domain.param.user.*
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteImagesDataSource: RemoteImagesDataSource,
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
    ) {
        val response = remoteUserDateSource.postUserSignIn(postUserSignInParam.toRequest())

        saveAccount(postUserSignInParam)
        saveToken(response)
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

    override suspend fun updateProfile(updateProfileParam: UpdateProfileParam) {
        val imageUrl = if (updateProfileParam.profileImage != null) {
            remoteImagesDataSource.postImages(
                listOf(updateProfileParam.profileImage!!.toMultipart())
            ).imageUrl.first()
        } else ""

        remoteUserDateSource.updateProfile(updateProfileParam.toRequest(imageUrl))
    }

    override suspend fun findUserAccount(phoneNumber: String): Flow<FindUserAccountEntity> =
        flow {
            emit(remoteUserDateSource.findUserAccount(phoneNumber).toEntity())
        }

    override suspend fun patchUserHealth(patchUserHealthParam: PatchUserHealthParam) =
        remoteUserDateSource.patchUserHealth(patchUserHealthParam.toRequest())

    override suspend fun signUpClass(signUpClassParam: SignUpClassParam) =
        remoteUserDateSource.signUpClass(
            signUpClassParam.group_id,
            signUpClassParam.toRequest()
        )

    override suspend fun patchSchool(schoolId: Int) =
        remoteUserDateSource.patchSchool(schoolId)

    override suspend fun autoLogin() {
        remoteUserDateSource.postUserSignIn(
            UserSignInRequest(
                localUserDataSource.fetchId(),
                localUserDataSource.fetchPw(),
                localUserDataSource.fetchDeviceToken()
            )
        )
    }

    override suspend fun patchDailyWalkGoal(patchDailyWalkGoalParam: PatchDailyWalkGoalParam) {
        remoteUserDateSource.patchDailyWalkGoal(patchDailyWalkGoalParam.toRequest())
    }

    override suspend fun fetchCaloriesLevel(): Flow<FetchCaloriesLevelEntity> =
        OfflineCacheUtil<FetchCaloriesLevelEntity>()
            .remoteData { remoteUserDateSource.fetchCaloriesLevelList().toEntity() }
            .localData { localUserDataSource.fetchCaloriesLevelList() }
            .doOnNeedRefresh { localUserDataSource.insertCaloriesLevelList(it) }
            .createFlow()

    private suspend fun saveToken(userSignInResponse: UserSignInResponse) {
        localUserDataSource.apply {
            setAccessToken(userSignInResponse.accessToken)
            setRefreshToken(userSignInResponse.refreshToken)
            setExpiredAt(userSignInResponse.expiredAt)
        }
    }

    private suspend fun saveAccount(userSignInParam: PostUserSignInParam) {
        localUserDataSource.apply {
            setId(userSignInParam.accountId)
            setPw(userSignInParam.password)
            setDeviceToken(userSignInParam.deviceToken)
        }
    }

    override suspend fun fetchUserProfile(userId: Int): Flow<UserProfileEntity> =
        OfflineCacheUtil<UserProfileEntity>()
            .remoteData { remoteUserDateSource.fetchUserProfile(userId).toEntity() }
            .localData { localUserDataSource.fetchUserProfile(userId) }
            .doOnNeedRefresh { localUserDataSource.insertUserProfile(it) }
            .createFlow()

    fun PatchDailyWalkGoalParam.toRequest() =
        PatchDailyWalkGoalRequest(
            dailyWalkCountGoal = dailyWalkCountGoal
        )

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

    fun UpdateProfileParam.toRequest(profileImageUrl: String) =
        UpdateProfileRequest(
            name = name,
            profileImageUrl = profileImageUrl,
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
            authCode = authCode,
            schoolName = schoolName
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