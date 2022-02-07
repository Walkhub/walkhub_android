package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.FindUserAccountResponse
import com.semicolon.data.remote.response.users.UserSignInResponse
import com.semicolon.data.remote.response.users.UserSignUpResponse
import com.semicolon.data.remote.response.users.UserReissueResponse
import com.semicolon.data.remote.response.users.FetchMyPageResponse
import com.semicolon.data.remote.response.users.FetchOwnBadgeResponse
import com.semicolon.data.remote.response.users.FetchUserProfileResponse
import retrofit2.http.*

interface UserApi {

    // 전화번호 인증(회원가입)
    @POST("users/signup/verification-codes")
    suspend fun verifyPhoneNumberSignUp(
        @Body verifyPhoneNumberSignUpRequest: VerifyPhoneNumberSignUpRequest
    )

    // 유저 회원가입
    @POST("users")
    suspend fun userSignUp(
        @Body userSignUpRequest: UserSignUpRequest
    ): UserSignUpResponse

    // 유저 로그인
    @POST("users/auth")
    suspend fun userSignIn(
        @Body userSignInRequest: UserSignInRequest
    ): UserSignInResponse

    // 토큰 재발급
    @PUT("users/reissue")
    suspend fun userReissue(
        @Header("x-refresh-token") refreshToken: String
    ): UserReissueResponse

    // 유저 비밀번호 변경
    @PATCH("users/password")
    suspend fun userChangePassword(
        @Body userChangePasswordRequest: UserChangePasswordRequest
    )

    // 유저 프로필 조회
    @GET("users/{user-id}")
    suspend fun fetchUserProfile(
        @Path("user-id") userId: Int
    ): FetchUserProfileResponse

    // 마이 페이지 조회
    @GET("users")
    suspend fun fetchMyPage(
    ): FetchMyPageResponse

    // 소유한 뱃지 목록 조회
    @GET("user/{user-id}/badges")
    suspend fun fetchOwnBadge(
        @Path("user-id") userId: Int
    ): FetchOwnBadgeResponse

    // 대표 뱃지 설정
    @PUT("users/badges/{badge-id}")
    suspend fun setRepresentativeBadge(
        @Path("badge-id") badgeId: Int
    )

    // 내 정보 수정
    @PATCH("users")
    suspend fun updateProfile(
        @Body updateProfileRequest: UpdateProfileRequest
    )

    // 유저 아이디 찾기
    @GET("users/accounts/{phone-number}")
    suspend fun findUserAccount(
        @Path("phone_number") phoneNumber: String
    ): FindUserAccountResponse

    // 건강 정보 입력
    @PUT("users/health")
    suspend fun patchUserHealth(
        @Body patchUserHealthRequest: PatchUserHealthRequest
    )

    // 반 가입하기
    @POST("users/classes/{agency-code}/{grade}/{class}")
    suspend fun signUpClass(
        @Path("agency-code") agencyCode: String,
        @Path("grade") grade: Int,
        @Path("class") classRoom: Int,
        @Body signUpClassRequest: SignUpClassRequest
    )

    // 학교 정보 수
    suspend fun patchSchool(
        @Body agency_code: String
    )
}