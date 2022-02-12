package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.SchoolApi
import com.semicolon.data.remote.datasource.RemoteSchoolDataSourceImpl
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.data.remote.response.school.toListEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoteSchoolDataSourceUnitTest {

    private val schoolApi = mock<SchoolApi>()

    private val remoteSchoolDataSource = RemoteSchoolDataSourceImpl(schoolApi)

    @Test
    fun testRemoteSchoolDataSource() {
        val schoolName = "my school"
        val searchSchoolResponse = SearchSchoolResponse(
            listOf(
                SearchSchoolResponse.SchoolInfo(
                    "code",
                    schoolName,
                    "image"
                )
            )
        )

        runBlocking {
            whenever(schoolApi.searchSchool(schoolName)).thenReturn(searchSchoolResponse)

            val dataSourceResult = remoteSchoolDataSource.searchSchool(schoolName)
            assertEquals(dataSourceResult, searchSchoolResponse.toListEntity())
        }
    }
}