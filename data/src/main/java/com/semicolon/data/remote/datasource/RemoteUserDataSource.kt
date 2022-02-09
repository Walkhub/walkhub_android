package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*

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
    ): FindUserAccountResponse

    suspend fun fetchUserProfile(
        userId: Int
    ): FetchUserProfileResponse

    suspend fun fetchMyPage(): FetchMyPageResponse

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
}