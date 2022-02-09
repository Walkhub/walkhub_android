package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteUserDataSourceImpl @Inject constructor(
    private val userApi: UserApi
) : RemoteUserDataSource {

    override suspend fun verifyUserPhoneNumber(
        verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.verifyPhoneNumberSignUp(verifyPhoneNumberSignUpRequest) }
        .sendRequest()

    override suspend fun postUserSignUp(
        userSignUpRequest: UserSignUpRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.userSignUp(userSignUpRequest) }
        .sendRequest()

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

    override suspend fun fetchMyPage(): FetchMyPageResponse =
        HttpHandler<FetchMyPageResponse>()
            .httpRequest { userApi.fetchMyPage() }
            .sendRequest()

    override suspend fun fetchUserProfile(userId: Int) =
        HttpHandler<FetchUserProfileResponse>()
            .httpRequest { userApi.fetchUserProfile(userId) }
            .sendRequest()

    override suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.updateProfile(updateProfileRequest) }
            .sendRequest()

    override suspend fun patchSchool(schoolId: Int) =
        HttpHandler<Unit>()
            .httpRequest { userApi.patchSchool(schoolId) }
            .sendRequest()

    override suspend fun findUserAccount(phoneNumber: String): FindUserAccountResponse =
        HttpHandler<FindUserAccountResponse>()
            .httpRequest { userApi.findUserAccount(phoneNumber) }
            .sendRequest()

    override suspend fun patchUserHealth(
        patchUserHealthRequest: PatchUserHealthRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.patchUserHealth(patchUserHealthRequest) }
        .sendRequest()

    override suspend fun signUpClass(
        groupId: Int, signUpClassRequest: SignUpClassRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.signUpClass(groupId, signUpClassRequest) }
        .sendRequest()

    override suspend fun userReissue(refreshToken: String): UserReissueResponse =
        HttpHandler<UserReissueResponse>()
            .httpRequest { userApi.userReissue(refreshToken) }
            .sendRequest()
}
