package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.request.users.inquirymypage.InquiryMypageResponse
import com.semicolon.data.remote.request.users.inquiryownerbadges.InquiryOwnerBadgesResponse
import com.semicolon.data.remote.request.users.userInquiryProfile.UserInquiryProfileResponse
import retrofit2.http.*

interface UsersAPI {

    // 전화번호 인증(회원가입)
    @POST("users/signup/verification-codes")
    suspend fun verifyPhoneNumberRegister(
        @Body verifyPhoneNumberRegisterRequest: VerifyPhoneNumberRegisterRequest
    ) : Void

    // 전화번호 인증(패스워드)
    @POST("users/passwords/verification-codes")
    suspend fun verifyPhoneNumberPassword(
        @Body verifyPhoneNumberPasswordRequest: VerifyPhoneNumberPasswordRequest
    ) : Void

    // 유저 회원가입
    @POST("users")
    suspend fun userRegister(
        @Body userRegisterReqeust: UserRegisterRequest
    ) : UserRegisterResponse

    // 유저 로그인
    @POST("users/auth")
    suspend fun userLogin(
        @Body userLoginRequest: UserLoginRequest
    ) : UserLoginResponse

    // 토큰 재발급
    @PUT("users/reissue")
    suspend fun userReissue(
        @Header("x-refresh-token") refreshToken: String
    ) : UserReissueResponse

    // 유저 비밀번호 변경
    @PATCH("users/password")
    suspend fun userChangePassword(
        @Body userChangePasswordRequest: UserChangePasswordRequest
    ) : Void


    // 유저 프로필 조회
    @GET("users/{user-id}")
    suspend fun userInquiryProfile(
        @Header("Authorization") accessToken: String,
        @Path("user-id") userId: Int
    ) : UserInquiryProfileResponse

    // 마이 페이지 조회
    @GET("users")
    suspend fun inquiryMypage(
        @Header("Authorization") accessToken: String
    ) : InquiryMypageResponse

    // 소유한 뱃지 목록 조회
    @GET("user/{user-id}/badges")
    suspend fun inquiryOwnerBadges(
        @Header("Authorization") accessToken: String,
        @Path("user-id") userId: Int
    ) : InquiryOwnerBadgesResponse

    // 대표 뱃지 설정
    @PUT("users/badges/{badge-id}")
    suspend fun settingRepresentativeBadge(
        @Header("Authorization") accessToken: String,
        @Path("badge-id") badgeId: Int
    ) : Void

    // 내 정보 수정
    @PATCH("users")
    suspend fun updateProfile(
        @Header("Authorization") accessToken: String,
        @Body updateProfileRequest: UpdateProfileRequest
    ) : Void

    // 유저 아이디 찾기
    @GET("users/accounts/{phone-number}")
    suspend fun findUserAccounts(
        @Path("phone_number") phoneNumber: String
    ) : FindUserAccountsResponse

    // 건강 정보 입력
    @PUT("users/health")
    suspend fun inputHealth(
        @Header("Authorization") accessToken: String,
        @Body inputHealthRequest: InputHealthRequest
    ) : Void

}