package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity

interface RemoteUserDataSource {
    suspend fun signUpClass(
        groupId: Int,
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
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )

    suspend fun patchDailyWalkGoal(
        patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    )

    suspend fun fetchCaloriesLevelList() : FetchCaloriesLevelEntity
}