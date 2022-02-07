package com.semicolon.data.local.pref

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {

    private var pref: SharedPreferences? = null

    fun getToken(): String {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        return pref!!.getString("accessToken", "empty")!!
    }

    fun getAutoLoginState(): Boolean {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        return pref!!.getBoolean("autoLogin", false)
    }

    fun getId(): String {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        return pref!!.getString("id", "empty")!!
    }

    fun getPw(): String {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        return pref!!.getString("pw", "empty")!!
    }

    fun getDeviceToken(): String {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        return pref!!.getString("deviceToken", "empty")!!
    }

    fun saveToken(token: String) {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("accessToken", token)
        editor.apply()
    }

    fun saveAutoLoginState(state: Boolean) {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putBoolean("autoLogin", state)
        editor.apply()
    }

    fun saveId(Id: String) {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("id", Id)
        editor.apply()
    }

    fun savePw(Pw: String) {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("pw", Pw)
        editor.apply()
    }

    fun saveDeviceToken(deviceToken: String) {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString("deviceToken", deviceToken)
        editor.apply()
    }

    fun clearAutoLoginState() {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        pref!!.edit().remove("autoLogin").apply()

    }

    fun clearToken() {
        if (pref == null) pref = context.getSharedPreferences("Walkhub", Context.MODE_PRIVATE)
        pref!!.edit().remove("accessToken").apply()
    }
}