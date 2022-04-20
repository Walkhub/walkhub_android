package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import com.semicolon.domain.param.user.VerifyPhoneNumberSignUpParam
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("users/classes")
    suspend fun signUpClass(
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

    @PATCH("users/schools")
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
    ): Response<UserSignInResponse>

    @POST("users/verification-codes")
    suspend fun verifyPhoneNumberSignUp(
        @Body verifyPhoneNumberSignUpParam: VerifyPhoneNumberSignUpParam
    ): Response<Void>

    @HEAD("users/verification-codes")
    suspend fun checkPhoneNumber(
        @Query("phoneNumber") phoneNumber: String,
        @Query("authCode") authCode: String
    ): Response<Void>

    @POST("users")
    suspend fun userSignUp(
        @Body userSignUpRequest: UserSignUpRequest
    ): Response<UserSignUpResponse>

    @PATCH("users/goal")
    suspend fun patchDailyWalkGoal(
        @Body patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    )

    @GET("users/levels/list")
    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelResponse

    @DELETE("users")
    suspend fun deleteAccount()

    @DELETE("users/classes")
    suspend fun deleteClass()

    @HEAD("users/account-id")
    suspend fun checkAccountOverlap(
        @Query("accountId") accountId: String
    ): Response<Void>

    @HEAD("users/classes")
    suspend fun checkClassCode(
        @Query("code") code: String
    )

    @PATCH("users/{user-id}/independence")
    suspend fun changeIndependence(
        @Path("user-id") userId: Int
    )

    @GET("users/goal")
    suspend fun fetchDailyGoal(): FetchDailyGoalResponse

    @GET("users/info")
    suspend fun fetchInfo(): FetchInfoResponse

    @GET("users/health")
    suspend fun fetchUserHealth(): FetchUserHealthResponse

    @GET("users/auth/info")
    suspend fun fetchAuthInfo(): FetchAuthInfoResponse
}