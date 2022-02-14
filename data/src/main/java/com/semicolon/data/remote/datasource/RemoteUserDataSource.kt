package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity

interface RemoteUserDataSource {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )

    suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    suspend fun patchUserChangePassword(
        userChangePasswordRequest: UserChangePasswordRequest
    )

    suspend fun fetchMyPage(): FetchMyPageResponse

    suspend fun findUserAccount(
        phoneNumber: String
    ): FindUserAccountEntity

    suspend fun fetchUserProfile(
        userId: Int
    ): UserProfileEntity

    suspend fun fetchUserOwnBadge(
        userId: Int
    ): FetchOwnBadgeResponse

    suspend fun fetchMyPage(): UserMyPageEntity

    suspend fun setBadge(
        badgeId: Int
    )

    suspend fun updateProfile(
        updateProfileRequest: UpdateProfileRequest
    )

    suspend fun findUserAccount(
        phoneNumber: String
    ): FindUserAccountResponse

    suspend fun patchUserHealth(
        patchUserHealthRequest: PatchUserHealthRequest
    )

    suspend fun signUpClass(
        agencyCode: String,
        grade: Int,
        classRoom: Int,
        signUpClassRequest: SignUpClassRequest
    )

    suspend fun patchSchool(
        agencyCode: String
    )

    suspend fun fetchCaloriesLevelList() : FetchCaloriesLevelEntity
}