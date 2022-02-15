package com.semicolon.domain.repository

import com.semicolon.domain.entity.school.SearchSchoolEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {

    suspend fun searchSchool(name: String): Flow<List<SearchSchoolEntity>>
}