package com.semicolon.data.local.datasource

import com.semicolon.domain.entity.school.SchoolDetailEntity

interface LocalSchoolDataSource {

    suspend fun fetchSchoolDetail(): SchoolDetailEntity

    suspend fun insertSchoolDetail(schoolDetailEntity: SchoolDetailEntity)
}