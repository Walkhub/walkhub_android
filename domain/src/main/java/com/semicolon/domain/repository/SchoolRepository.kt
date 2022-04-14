package com.semicolon.domain.repository

import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SchoolRepository {

    suspend fun setSchoolLogo(profileImage: File)

    suspend fun fetchSchoolDetail(schoolId: Int): Flow<SchoolDetailEntity>

    suspend fun searchSchool(name: String): Flow<SearchSchoolEntity>
}