package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.datasource.RemoteSchoolDataSource
import com.semicolon.domain.entity.school.SearchSchoolEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SchoolRepositoryUnitTest {

    private val schoolDataSource = mock<RemoteSchoolDataSource>()

    private val schoolRepository = SchoolRepositoryImpl(schoolDataSource)

    @Test
    fun testSearchSchool() {
        val schoolName = "my school"
        val searchSchoolResponse =
            listOf(
                SearchSchoolEntity(
                    "code",
                    schoolName,
                    "image"
                )
            )

        runBlocking {
            whenever(schoolDataSource.searchSchool(schoolName)).thenReturn(searchSchoolResponse)

            val repositoryResult = schoolRepository.searchSchool(schoolName)
            repositoryResult.collect {
                assertEquals(searchSchoolResponse,it)
            }
        }
    }
}