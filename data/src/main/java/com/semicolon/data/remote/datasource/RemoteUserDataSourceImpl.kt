package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.users.*
import com.semicolon.domain.exception.*
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : RemoteUserDataSource {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ) {
        val response = userApi.verifyPhoneNumberSignUp(verifyPhoneNumberSignUpParam)
        if (!response.isSuccessful) {
            throw when (response.code()) {
                401 -> UnauthorizedException()
                404 -> NotFoundException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun checkPhoneNumber(
        checkPhoneNumberParam: CheckPhoneNumberParam
    ) {
        val response = userApi.checkPhoneNumber(
            phoneNumber = checkPhoneNumberParam.phoneNumber,
            authCode = checkPhoneNumberParam.authCode
        )
        if (!response.isSuccessful) {
            throw when (response.code()) {
                401 -> UnauthorizedException()
                404 -> NotFoundException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    ): UserSignUpResponse = HttpHandler<UserSignUpResponse>()
        .httpRequest { userApi.userSignUp(userSignUpRequest) }
        .sendRequest()

    override suspend fun patchDailyWalkGoal(
        patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.patchDailyWalkGoal(patchDailyWalkGoalRequest) }
        .sendRequest()

    override suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelEntity =
        HttpHandler<FetchCaloriesLevelResponse>()
            .httpRequest { userApi.fetchCaloriesLevelList() }
            .sendRequest().toEntity()

    override suspend fun deleteAccount() =
        HttpHandler<Unit>()
            .httpRequest { userApi.deleteAccount() }
            .sendRequest()

    override suspend fun deleteClass() =
        HttpHandler<Unit>()
            .httpRequest { userApi.deleteClass() }
            .sendRequest()

    override suspend fun checkAccountOverlap(
        accountId: String
    ) {
        val response = userApi.checkAccountOverlap(accountId = accountId)
        if (!response.isSuccessful) {
            throw when (response.code()) {
                404 -> NotFoundException()
                409 -> ConflictException()
                else -> UnknownException()
            }
        }
    }

    override suspend fun checkClassCode(code: String) =
        HttpHandler<Unit>()
            .httpRequest { userApi.checkClassCode(code) }
            .sendRequest()

    override suspend fun changeIndependence(userId: Int) =
        HttpHandler<Unit>()
            .httpRequest { userApi.changeIndependence(userId) }
            .sendRequest()

    override suspend fun fetchDailyGoal(): FetchDailyGoalEntity =
        HttpHandler<FetchDailyGoalResponse>()
            .httpRequest { userApi.fetchDailyGoal() }
            .sendRequest().toEntity()

    override suspend fun fetchInfo(): FetchInfoEntity =
        HttpHandler<FetchInfoResponse>()
            .httpRequest { userApi.fetchInfo() }
            .sendRequest().toEntity()

    override suspend fun fetchUserHealth(): FetchUserHealthEntity =
        HttpHandler<FetchUserHealthResponse>()
            .httpRequest { userApi.fetchUserHealth() }
            .sendRequest().toEntity()

    override suspend fun fetchAuthInfo(): FetchAuthInfoEntity =
        HttpHandler<FetchAuthInfoResponse>()
            .httpRequest { userApi.fetchAuthInfo() }
            .sendRequest().toEntity()

    override suspend fun deleteDeviceToken() {
        HttpHandler<Unit>()
            .httpRequest { userApi.deleteDeviceToken() }
            .sendRequest()
    }

    override suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest
    ) = HttpHandler<UserSignInResponse>()
        .httpRequest { userApi.userSignIn(userSignInRequest) }
        .sendRequest()

    override suspend fun patchUserChangePassword(
        userChangePasswordRequest: UserChangePasswordRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.userChangePassword(userChangePasswordRequest) }
        .sendRequest()

    override suspend fun verifyPassword(checkPasswordRequest: CheckPasswordRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.verifyPassword(checkPasswordRequest) }
            .sendRequest()

    override suspend fun fetchMyPage(): UserMyPageEntity =
        HttpHandler<FetchMyPageResponse>()
            .httpRequest { userApi.fetchMyPage() }
            .sendRequest().toEntity()

    override suspend fun fetchUserProfile(userId: Int): UserProfileEntity =
        HttpHandler<FetchUserProfileResponse>()
            .httpRequest { userApi.fetchUserProfile(userId) }
            .sendRequest().toEntity()

    override suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.updateProfile(updateProfileRequest) }
            .sendRequest()

    override suspend fun patchSchool(schoolId: Int) =
        HttpHandler<Unit>()
            .httpRequest { userApi.patchSchool(schoolId) }
            .sendRequest()

    override suspend fun findUserAccount(phoneNumber: String): FindUserAccountEntity =
        HttpHandler<FindUserAccountResponse>()
            .httpRequest { userApi.findUserAccount(phoneNumber) }
            .sendRequest().toEntity()

    override suspend fun patchUserHealth(
        patchUserHealthRequest: PatchUserHealthRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.patchUserHealth(patchUserHealthRequest) }
        .sendRequest()

    override suspend fun signUpClass(
        signUpClassRequest: SignUpClassRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.signUpClass(signUpClassRequest) }
        .sendRequest()

    override suspend fun userReissue(refreshToken: String): UserReissueResponse =
        HttpHandler<UserReissueResponse>()
            .httpRequest { userApi.userReissue(refreshToken) }
            .sendRequest()

}