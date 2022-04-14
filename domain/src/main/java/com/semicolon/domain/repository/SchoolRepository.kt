package com.semicolon.domain.repository

import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity
import kotlinx.coroutines.flow.Flow
import java.io.File

interface SchoolRepository {

    suspend fun searchSchool(name: String): Flow<List<SearchSchoolEntity>>

    suspend fun setSchoolLogo(profileImage: File)

    suspend fun fetchSchoolDetail(schoolId: Int): Flow<SchoolDetailEntity>
}