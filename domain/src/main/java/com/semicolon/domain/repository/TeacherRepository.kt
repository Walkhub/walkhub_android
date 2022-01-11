package com.semicolon.domain.repository

import com.semicolon.domain.param.teacher.CreateClassParam
import com.semicolon.domain.param.teacher.DeleteClassParam
import kotlinx.coroutines.flow.Flow

interface TeacherRepository {

    fun produceTeacherVerifyCode(): Flow<String>

    fun createClass(createClassParam: CreateClassParam): Flow<String>

    fun deleteClass(deleteClassParam: DeleteClassParam)
}