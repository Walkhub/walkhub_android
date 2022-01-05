package com.semicolon.data.remote.api

import com.semicolon.data.remote.request.VerifyRequest
import retrofit2.http.Body
import retrofit2.http.GET

interface UsersAPI {

    // 전화번호 인증(회원가입)
    @GET("users/verify")
    suspend fun verifyPhoneNumber(
        @Body verifyRequest: VerifyRequest
    ) : Void

    // 전화번호 인증(패스워드)

    // 유저 회원가입

    // 유저 로그인

    // 토큰 재발급

    // 유저 비밀번호 변경

    //  유저 프로필 조회

}