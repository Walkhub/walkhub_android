package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.*
import retrofit2.http.*

interface UserApi {

    // 반 가입하기 o
    @POST("users/classes/{group-id}")
    suspend fun signUpClass(
        @Path("group-id") groupId: Int,
        @Body signUpClassRequest: SignUpClassRequest
    )

    // 토큰 재발급 o
    @PATCH("users/token")
    suspend fun userReissue(
        @Header("Refresh-Token") refreshToken: String
    ): UserReissueResponse

    // 건강 정보 입력 o
    @PATCH("users/health")
    suspend fun patchUserHealth(
        @Body patchUserHealthRequest: PatchUserHealthRequest
    )

    // 내 정보 수정 o
    @PATCH("users")
    suspend fun updateProfile(
        @Body updateProfileRequest: UpdateProfileRequest
    )

    // 학교 정보 수정 o
    @PATCH("users/school")
    suspend fun patchSchool(
        @Body schoolId: Int
    )

    // 유저 아이디 찾기 o
    @GET("users/accounts/{phone-number}")
    suspend fun findUserAccount(
        @Path("phone_number") phoneNumber: String
    ): FindUserAccountResponse

    // 유저 프로필 조회 o
    @GET("users/{user-id}")
    suspend fun fetchUserProfile(
        @Path("user-id") userId: Int
    ): FetchUserProfileResponse

    // 마이 페이지 조회 o
    @GET("users")
    suspend fun fetchMyPage(
    ): FetchMyPageResponse

    // 유저 비밀번호 변경 o
    @PATCH("users/password")
    suspend fun userChangePassword(
        @Body userChangePasswordRequest: UserChangePasswordRequest
    )

    // 유저 로그인 o
    @POST("users/token")
    suspend fun userSignIn(
        @Body userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    // 전화번호 인증 o
    @POST("users/verification-codes")
    suspend fun verifyPhoneNumberSignUp(
        @Body verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    // 유저 회원가입 o
    @POST("users")
    suspend fun userSignUp(
        @Body userSignUpRequest: UserSignUpRequest
    ): UserSignUpResponse

    // 하루 목표 걸음 수 변경
    @PATCH("users/goal")
    suspend fun patchDailyWalkGoal(
        @Body patchDailyWalkGoalRequest: PatchDailyWalkGoalRequest
    )

    // 칼로리 레벨 목록 조회
    @GET("users/levels/list")
    suspend fun fetchCaloriesLevelList(): FetchCaloriesLevelResponse
}