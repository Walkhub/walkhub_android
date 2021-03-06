package com.semicolon.data.repository

import com.google.firebase.messaging.FirebaseMessaging
import com.semicolon.data.local.datasource.LocalUserDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteUserDataSource
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.util.OfflineCacheUtil
import com.semicolon.data.util.toMultipart
import com.semicolon.domain.entity.users.*
import com.semicolon.domain.exception.NoInternetException
import com.semicolon.domain.param.user.*
import com.semicolon.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepositoryImpl @Inject constructor(
    private val remoteImagesDataSource: RemoteImagesDataSource,
    private val localUserDataSource: LocalUserDataSource,
    private val remoteUserDateSource: RemoteUserDataSource
) : UserRepository {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ) = remoteUserDateSource.verifyUserPhoneNumber(verifyPhoneNumberSignUpParam)

    override suspend fun checkPhoneNumber(
        checkPhoneNumberParam: CheckPhoneNumberParam
    ) = remoteUserDateSource.checkPhoneNumber(checkPhoneNumberParam)

    override suspend fun postUserSignUp(
        postUserSignUpParam: PostUserSignUpParam
    ) {
        val postUserSignInParam = PostUserSignInParam(
            accountId = postUserSignUpParam.accountId,
            password = postUserSignUpParam.password
        )

        val token = suspendCoroutine<String> {
            FirebaseMessaging.getInstance().token.addOnSuccessListener { token -> it.resume(token) }
        }

        val response = remoteUserDateSource.postUserSignUp(postUserSignUpParam.toRequest(token))

        saveAccount(postUserSignInParam, token)
        saveTokenSignUp(
            accessToken = response.accessToken,
            refreshToken = response.refreshToken,
            expiredAt = response.expiredAt
        )
    }

    override suspend fun postUserSignIn(
        postUserSignInParam: PostUserSignInParam
    ) {
        val token = suspendCoroutine<String> {
            FirebaseMessaging.getInstance().token.addOnSuccessListener { token -> it.resume(token) }
        }
        val response = remoteUserDateSource.postUserSignIn(postUserSignInParam.toRequest(token))
        saveAccount(postUserSignInParam, token)
        saveToken(response)
    }

    override suspend fun verifyPassword(
        verifyPasswordParam: VerifyPasswordParam
    ) = remoteUserDateSource.verifyPassword(verifyPasswordParam.toRequest())

    override suspend fun patchUserChangePassword(
        patchUserChangePasswordParam: PatchUserChangePasswordParam
    ) {
        remoteUserDateSource.patchUserChangePassword(patchUserChangePasswordParam.toRequest())
    }

    override suspend fun fetchMyPage(): Flow<UserMyPageEntity> =
        OfflineCacheUtil<UserMyPageEntity>()
            .remoteData { remoteUserDateSource.fetchMyPage() }
            .localData { localUserDataSource.fetchUserMyPage() }
            .doOnNeedRefresh { localUserDataSource.insertUserMyPage(it) }
            .createFlow()

    override suspend fun updateProfile(updateProfileParam: UpdateProfileParam) {
        val profileImageUrl = updateProfileParam.profileImage?.let {
            remoteImagesDataSource.postImages(listOf(it.toMultipart())).imageUrl[0]
        } ?: ""
        remoteUserDateSource.updateProfile(
            updateProfileParam.toRequest(profileImageUrl)
        )
    }

    override suspend fun findUserAccount(phoneNumber: String): Flow<FindUserAccountEntity> =
        flow {
            emit(remoteUserDateSource.findUserAccount(phoneNumber))
        }

    override suspend fun patchUserHealth(patchUserHealthParam: PatchUserHealthParam) =
        remoteUserDateSource.patchUserHealth(patchUserHealthParam.toRequest())

    override suspend fun signUpClass(signUpClassParam: SignUpClassParam) =
        remoteUserDateSource.signUpClass(
            signUpClassParam.toRequest()
        )

    override suspend fun patchSchool(schoolId: Int) =
        remoteUserDateSource.patchSchool(schoolId)

    override suspend fun autoLogin() {
        val id = localUserDataSource.fetchId()
        val password = localUserDataSource.fetchPw()
        val deviceToken = localUserDataSource.fetchDeviceToken()
        try {
            remoteUserDateSource.postUserSignIn(UserSignInRequest(id, password, deviceToken))
        } catch (e: NoInternetException) {
            if (id.isEmpty() && password.isEmpty()) throw e
        }
    }

    override suspend fun patchDailyWalkGoal(patchDailyWalkGoalParam: PatchDailyWalkGoalParam) {
        remoteUserDateSource.patchDailyWalkGoal(patchDailyWalkGoalParam.toRequest())
    }

    override suspend fun fetchCaloriesLevel(): Flow<FetchCaloriesLevelEntity> =
        OfflineCacheUtil<FetchCaloriesLevelEntity>()
            .remoteData { remoteUserDateSource.fetchCaloriesLevelList() }
            .localData { localUserDataSource.fetchCaloriesLevelList() }
            .doOnNeedRefresh { localUserDataSource.insertCaloriesLevelList(it) }
            .createFlow()

    override suspend fun deleteAccount() {
        remoteUserDateSource.deleteAccount()
    }

    override suspend fun deleteClass() {
        remoteUserDateSource.deleteClass()
    }

    override suspend fun checkAccountOverlap(accountId: String) {
        remoteUserDateSource.checkAccountOverlap(accountId)
    }

    override suspend fun checkClassCode(code: String) {
        remoteUserDateSource.checkClassCode(code)
    }

    override suspend fun changeIndependence(userId: Int) {
        remoteUserDateSource.changeIndependence(userId)
    }

    override suspend fun fetchDailyGoal(): Flow<FetchDailyGoalEntity> =
        OfflineCacheUtil<FetchDailyGoalEntity>()
            .remoteData { remoteUserDateSource.fetchDailyGoal() }
            .localData { localUserDataSource.fetchDailyGoal() }
            .doOnNeedRefresh { localUserDataSource.insertDailyGoal(it) }
            .createFlow()

    override suspend fun fetchInfo(): Flow<FetchInfoEntity> =
        OfflineCacheUtil<FetchInfoEntity>()
            .remoteData { remoteUserDateSource.fetchInfo() }
            .localData { localUserDataSource.fetchInfo() }
            .doOnNeedRefresh { localUserDataSource.insertInfo(it) }
            .createFlow()

    override suspend fun fetchUserHealth(): Flow<FetchUserHealthEntity> =
        OfflineCacheUtil<FetchUserHealthEntity>()
            .remoteData { remoteUserDateSource.fetchUserHealth() }
            .localData { localUserDataSource.fetchUserHealth() }
            .doOnNeedRefresh { localUserDataSource.insertUserHealth(it) }
            .createFlow()

    override suspend fun fetchAuthInfo(): Flow<FetchAuthInfoEntity> =
        OfflineCacheUtil<FetchAuthInfoEntity>()
            .remoteData { remoteUserDateSource.fetchAuthInfo() }
            .localData { localUserDataSource.fetchAuthInfo() }
            .doOnNeedRefresh { localUserDataSource.insertAuthInfo(it) }
            .createFlow()

    private suspend fun saveToken(userSignInResponse: UserSignInResponse) {
        localUserDataSource.apply {
            setAccessToken(userSignInResponse.accessToken)
            setRefreshToken(userSignInResponse.refreshToken)
            setExpiredAt(userSignInResponse.expiredAt)
        }
    }

    private suspend fun saveTokenSignUp(
        accessToken: String,
        refreshToken: String,
        expiredAt: String
    ) {
        localUserDataSource.apply {
            setAccessToken(accessToken)
            setRefreshToken(refreshToken)
            setExpiredAt(expiredAt)
        }
    }

    private suspend fun saveAccount(userSignInParam: PostUserSignInParam, deviceToken: String) {
        localUserDataSource.apply {
            setId(userSignInParam.accountId)
            setPw(userSignInParam.password)
            setDeviceToken(deviceToken)
        }
    }

    override suspend fun logout() {
        localUserDataSource.clearId()
        localUserDataSource.clearPw()
        localUserDataSource.clearAccessToken()
        localUserDataSource.clearRefreshToken()
    }

    override suspend fun fetchUserProfile(userId: Int): Flow<UserProfileEntity> =
        OfflineCacheUtil<UserProfileEntity>()
            .remoteData { remoteUserDateSource.fetchUserProfile(userId) }
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
            weight = weight,
            sex = sex
        )

    fun UpdateProfileParam.toRequest(profileImageUrl: String) =
        UpdateProfileRequest(
            name = name,
            profileImageUrl = profileImageUrl,
            schoolId = schoolId
        )

    fun VerifyPhoneNumberSignUpParam.toRequest() =
        VerifyPhoneNumberSignUpRequest(
            phoneNumber = phone_number
        )

    fun VerifyPasswordParam.toRequest() =
        CheckPasswordRequest(
            password = password
        )

    fun PostUserSignUpParam.toRequest(token: String) =
        UserSignUpRequest(
            accountId = accountId,
            password = password,
            name = name,
            phoneNumber = phoneNumber,
            height = height,
            weight = weight,
            sex = sex,
            schoolId = schoolId,
            authCode = authCode,
            deviceToken = token
        )

    fun PostUserSignInParam.toRequest(deviceToken: String) =
        UserSignInRequest(
            accountId = accountId,
            password = password,
            deviceToken = deviceToken
        )

    fun PatchUserChangePasswordParam.toRequest() =
        UserChangePasswordRequest(
            password = password,
            newPassword = newPassword
        )
}