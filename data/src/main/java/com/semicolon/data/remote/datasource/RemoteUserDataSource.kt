package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.domain.entity.users.*
import com.semicolon.domain.param.user.CheckPhoneNumberParam
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam

interface RemoteUserDataSource {
    suspend fun signUpClass(
        signUpClassRequest: SignUpClassRequest
    )

    suspend fun userReissue(
        refreshToken: String
    ): UserReissueResponse

    suspend fun patchUserHealth(
        patchUserHealthRequest: PatchUserHealthRequest
    )

    suspend fun updateProfile(
        updateProfileRequest: UpdateProfileRequest
    )

    suspend fun patchSchool(
        schoolId: Int
    )

    suspend fun findUserAccount(
        phoneNumber: String
    ): FindUserAccountEntity

    suspend fun fetchUserProfile(
        userId: Int
    ): UserProfileEntity

    suspend fun fetchMyPage(): UserMyPageEntity

    suspend fun patchUserChangePassword(
        userChangePasswordRequest: UserChangePasswordRequest
    )

    suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    )

    suspend fun checkPhoneNumber(
        checkPhoneNumberParam: CheckPhoneNumberParam
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )

    suspend fun patchDailyWalkGoal(
        patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    )

    suspend fun fetchCaloriesLevelList() : FetchCaloriesLevelEntity

    suspend fun deleteAccount()

    suspend fun deleteClass()

    suspend fun checkAccountOverlap(accountId: String)

    suspend fun checkClassCode(code: String)

    suspend fun changeIndependence(userId: Int)

    suspend fun fetchDailyGoal(): FetchDailyGoalEntity

    suspend fun fetchInfo(): FetchInfoEntity

    suspend fun fetchUserHealth(): FetchUserHealthEntity

    suspend fun fetchAuthInfo(): FetchAuthInfoEntity

    suspend fun deleteDeviceToken()
}

