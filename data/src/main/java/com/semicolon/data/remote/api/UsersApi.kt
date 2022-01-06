package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.users.*
import com.semicolon.data.remote.response.users.FindUserAccountsResponse
import com.semicolon.data.remote.response.users.UserLoginResponse
import com.semicolon.data.remote.response.users.UserRegisterResponse
import com.semicolon.data.remote.response.users.UserReissueResponse
import com.semicolon.data.remote.response.users.inquirymypage.InquiryMypageResponse
import com.semicolon.data.remote.response.users.inquiryownerbadges.InquiryOwnerBadgesResponse
import com.semicolon.data.remote.response.users.userInquiryProfile.UserInquiryProfileResponse
import retrofit2.http.*

interface UsersApi {

    // 전화번호 인증(회원가입)
    @POST("users/signup/verification-codes")
    suspend fun verifyPhoneNumberRegister(
        @Body verifyPhoneNumberRegisterRequest: VerifyPhoneNumberRegisterRequest
    ) : Unit

    // 전화번호 인증(패스워드)
    @POST("users/passwords/verification-codes")
    suspend fun verifyPhoneNumberPassword(
        @Body verifyPhoneNumberPasswordRequest: VerifyPhoneNumberPasswordRequest
    ) : Unit

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
    ) : Unit


    // 유저 프로필 조회
    @GET("users/{user-id}")
    suspend fun userInquiryProfile(
        @Path("user-id") userId: Int
    ) : UserInquiryProfileResponse

    // 마이 페이지 조회
    @GET("users")
    suspend fun inquiryMypage(
    ) : InquiryMypageResponse

    // 소유한 뱃지 목록 조회
    @GET("user/{user-id}/badges")
    suspend fun inquiryOwnerBadges(
        @Path("user-id") userId: Int
    ) : InquiryOwnerBadgesResponse

    // 대표 뱃지 설정
    @PUT("users/badges/{badge-id}")
    suspend fun settingRepresentativeBadge(
        @Path("badge-id") badgeId: Int
    ) : Unit

    // 내 정보 수정
    @PATCH("users")
    suspend fun updateProfile(
        @Body updateProfileRequest: UpdateProfileRequest
    ) : Unit

    // 유저 아이디 찾기
    @GET("users/accounts/{phone-number}")
    suspend fun findUserAccounts(
        @Path("phone_number") phoneNumber: String
    ) : FindUserAccountsResponse

    // 건강 정보 입력
    @PUT("users/health")
    suspend fun inputHealth(
        @Body inputHealthRequest: InputHealthRequest
    ) : Unit

}