package com.semicolon.data.remote.response.teacher

import com.google.gson.annotations.SerializedName

data class TeacherVerifyCodeResponse(
    @SerializedName("code") val code : String
)