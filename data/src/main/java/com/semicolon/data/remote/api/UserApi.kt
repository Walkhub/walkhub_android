package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import retrofit2.http.*

interface UserApi {

    @POST("users/classes/{section-id}")
    suspend fun signUpClass(
        @Path("section_id") groupId: Int,
        @Body signUpClassRequest: SignUpClassRequest
    )

    @PATCH("users/token")
    suspend fun userReissue(
        @Header("Refresh-Token") refreshToken: String
    ): UserReissueResponse

    @PATCH("users/health")
    suspend fun patchUserHealth(
        @Body patchUserHealthRequest: PatchUserHealthRequest
    )

    @PATCH("users")
    suspend fun updateProfile(
        @Body updateProfileRequest: UpdateProfileRequest
    )

    @PATCH("users/school")
    suspend fun patchSchool(
        @Body school_id: Int
    )

    @GET("users/accounts/{phone-number}")
    suspend fun findUserAccount(
        @Path("phone_number") phoneNumber: String
    ): FindUserAccountResponse

    @GET("users/{user-id}")
    suspend fun fetchUserProfile(
        @Path("user-id") userId: Int
    ): FetchUserProfileResponse

    @GET("users")
    suspend fun fetchMyPage(
    ): FetchMyPageResponse

    @PATCH("users/password")
    suspend fun userChangePassword(
        @Body userChangePasswordRequest: UserChangePasswordRequest
    )

    @POST("users/token")
    suspend fun userSignIn(
        @Body userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    @POST("users/verification-codes")
    suspend fun verifyPhoneNumberSignUp(
        @Body verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    @POST("users")
    suspend fun userSignUp(
        @Body userSignUpRequest: UserSignUpRequest
    ): UserSignUpResponse

    @PATCH("users/goal")
    suspend fun patchDailyWalkGoal(
        @Body patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    )
    
    @GET("users/levels/list")
    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelResponse
}