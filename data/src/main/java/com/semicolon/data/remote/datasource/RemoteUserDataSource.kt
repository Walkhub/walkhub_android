package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.users.UserChangePasswordRequest
import com.semicolon.data.remote.request.users.UserSignInRequest
import com.semicolon.data.remote.request.users.UserSignUpRequest
import com.semicolon.data.remote.request.users.VerifyPhoneNumberSignUpRequest
import com.semicolon.data.remote.response.users.FetchMyPageResponse
import com.semicolon.data.remote.response.users.FetchOwnBadgeResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.remote.response.users.FetchUserProfileResponse

interface RemoteUserDataSource {
    suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    )

    suspend fun postUserSignIn(
        userSignInRequest: UserSignInRequest
    ) : UserSignInResponse

    suspend fun patchUserChangePassword(
        userChangePasswordRequest: UserChangePasswordRequest
    )

    suspend fun fetchMyPage() : FetchMyPageResponse

    suspend fun fetchUserProfile(
        userId: Int
    ) : FetchUserProfileResponse

    suspend fun fetchUserOwnBadge(
        userId: Int
    ) : FetchOwnBadgeResponse
}