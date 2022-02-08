package com.semicolon.data.local.storage

import android.content.Context
import android.content.SharedPreferences
import org.threeten.bp.LocalDateTime

interface AuthDataStorage {

    fun setAccessToken(token: String)
    fun fetchAccessToken(): String
    fun clearAccessToken()

    fun setRefreshToken(token: String)
    fun fetchRefreshToken(): String
    fun clearRefreshToken()

    fun setExpiredAt(localDateTime: String)
    fun fetchExpiredAt(): LocalDateTime

    fun setDeviceToken(deviceToken: String)
    fun fetchDeviceToken(): String

    fun setId(id: String)
    fun fetchId(): String

    fun setPw(pw: String)
    fun fetchPw(): String
}