package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.request.teacher.CreateClassRequest
import com.semicolon.data.remote.request.teacher.DeleteClassRequest
import com.semicolon.data.remote.response.teacher.TeacherVerifyCodeResponse

interface RemoteTeacherDataSource {

    fun produceTeacherVerifyCode(): TeacherVerifyCodeResponse

    fun createClass(createClassRequest: CreateClassRequest): TeacherVerifyCodeResponse

    fun deleteClass(deleteClassRequest: DeleteClassRequest)
}