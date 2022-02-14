package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.UserApi
import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.users.FetchCaloriesLevelEntity
import com.semicolon.domain.entity.users.FindUserAccountEntity
import com.semicolon.domain.entity.users.UserMyPageEntity
import com.semicolon.domain.entity.users.UserProfileEntity
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

<<<<<<< HEAD
=======
    override suspend fun patchDailyWalkGoal(
        patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.patchDailyWalkGoal(patchDailyWalkGoalRequest) }
        .sendRequest()

    override suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelEntity =
        HttpHandler<FetchCaloriesLevelResponse>()
        .httpRequest { userApi.fetchCaloriesLevelList() }
        .sendRequest().toEntity()

>>>>>>> 60_Notice_data
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

    override suspend fun fetchMyPage(): UserMyPageEntity =
        HttpHandler<FetchMyPageResponse>()
            .httpRequest { userApi.fetchMyPage() }
            .sendRequest().toEntity()

    override suspend fun fetchUserProfile(userId: Int): UserProfileEntity =
        HttpHandler<FetchUserProfileResponse>()
            .httpRequest { userApi.fetchUserProfile(userId) }
            .sendRequest().toEntity()

    override suspend fun fetchUserOwnBadge(userId: Int) =
        HttpHandler<FetchOwnBadgeResponse>()
            .httpRequest { userApi.fetchOwnBadge(userId) }
            .sendRequest()

    override suspend fun setBadge(badgeId: Int) =
        HttpHandler<Unit>()
            .httpRequest { userApi.setRepresentativeBadge(badgeId) }
            .sendRequest()

    override suspend fun updateProfile(updateProfileRequest: UpdateProfileRequest) =
        HttpHandler<Unit>()
            .httpRequest { userApi.updateProfile(updateProfileRequest) }
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
        agencyCode: String,
        grade: Int,
        classRoom: Int,
        signUpClassRequest: SignUpClassRequest
    ) = HttpHandler<Unit>()
        .httpRequest { userApi.signUpClass(agencyCode, grade, classRoom, signUpClassRequest) }
        .sendRequest()

    override suspend fun patchSchool(agencyCode: String) =
        HttpHandler<Unit>()
            .httpRequest { userApi.patchSchool(agencyCode) }
            .sendRequest()
}