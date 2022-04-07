package com.semicolon.data.local.datasource

import com.semicolon.data.local.dao.SchoolDao
import com.semicolon.data.local.entity.school.toDbEntity
import com.semicolon.data.local.entity.school.toEntity
import com.semicolon.domain.entity.school.SchoolDetailEntity
import javax.inject.Inject

class LocalSchoolDataSourceImpl @Inject constructor(
    private val schoolDao: SchoolDao
) : LocalSchoolDataSource {

    override suspend fun fetchSchoolDetail(): SchoolDetailEntity =
        schoolDao.fetchSchoolDetail().toEntity()

    override suspend fun insertSchoolDetail(schoolDetailEntity: SchoolDetailEntity) {
        schoolDao.insertSchoolDetail(schoolDetailEntity.toDbEntity())
    }
}