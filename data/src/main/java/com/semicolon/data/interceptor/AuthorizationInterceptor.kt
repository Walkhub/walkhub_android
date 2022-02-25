package com.semicolon.data.interceptor

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.semicolon.data.local.storage.AuthDataStorage
import okhttp3.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import java.lang.RuntimeException
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val authDataStorage: AuthDataStorage
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/users/verification-codes",
            "/users/account-id",
            "/users/token",
            "/users/password"
        )
        if (ignorePath.contains(path)) return chain.proceed(request)
        if (path == "/users" && request.method() == "POST") return chain.proceed(request)
        if (path.contains("/users/accounts/")) return chain.proceed(request)

        val expiredAt = authDataStorage.fetchExpiredAt()
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = authDataStorage.fetchRefreshToken()

            val tokenRefreshRequest = Request.Builder()
                .url("https://server.walkhub.co.kr/users/token")
                .patch(RequestBody.create(MediaType.parse("application/json"), ""))
                .addHeader("Refresh-Token", "Bearer $refreshToken")
                .build()
            val response = client.newCall(tokenRefreshRequest).execute()

            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body()!!.toString(),
                    TokenRefreshResponse::class.java
                )
                authDataStorage.setAccessToken(token.accessToken)
                authDataStorage.setExpiredAt(token.expiredAt)
            } else throw RuntimeException()
        }

        val accessToken = authDataStorage.fetchAccessToken()
        return chain.proceed(
            request.newBuilder().addHeader(
                "Authorization",
                "Bearer $accessToken"
            ).build()
        )
    }

    data class TokenRefreshResponse(
        @SerializedName("access_token") val accessToken: String,
        @SerializedName("expired_at") val expiredAt: String,
    )
}