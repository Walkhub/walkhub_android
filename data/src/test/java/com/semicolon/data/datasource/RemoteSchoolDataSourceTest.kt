package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.remote.api.SchoolApi
import com.semicolon.data.remote.datasource.RemoteSchoolDataSourceImpl
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class RemoteSchoolDataSourceTest {

    private val schoolApi = mock<SchoolApi>()

    private val remoteSchoolDataSource = RemoteSchoolDataSourceImpl(schoolApi)

    @Test
    fun testRemoteSchoolDataSource() {
        val data = SearchSchoolResponse(
            listOf(
                SearchSchoolResponse.SchoolInfo(
                    "code",
                    "my school",
                    "image "
                )
            )
        )
        
        runBlocking {
            whenever(remoteSchoolDataSource.searchSchool("my school")).thenReturn(data)
            assertEquals(remoteSchoolDataSource.searchSchool("my school"), data)
        }

    }
}