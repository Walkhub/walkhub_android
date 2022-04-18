package com.semicolon.data.repository

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.data.local.datasource.LocalSchoolDataSource
import com.semicolon.data.remote.datasource.RemoteImagesDataSource
import com.semicolon.data.remote.datasource.RemoteSchoolDataSource
import com.semicolon.data.remote.response.school.SearchSchoolResponse
import com.semicolon.domain.entity.school.SearchSchoolEntity
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SchoolRepositoryUnitTest {

    private val remoteImagesDataSource = mock<RemoteImagesDataSource>()
    private val localSchoolDataSource = mock<LocalSchoolDataSource>()
    private val remoteSchoolDataSource = mock<RemoteSchoolDataSource>()

    private val schoolRepository =
        SchoolRepositoryImpl(remoteImagesDataSource, localSchoolDataSource, remoteSchoolDataSource)

    @Test
    fun testSearchSchool() {
//        val schoolName = "my school"
//        val schoolId = 1
//        val image = "image"
//
//        val searchSchoolResponse =
//            SearchSchoolResponse(
//                schoolList = listOf(
//                    SearchSchoolResponse.SchoolInfo(
//                        schoolId,
//                        image,
//                        schoolName
//                    )
//                )
//            )
//
//
//        runBlocking {
//            whenever(remoteSchoolDataSource.searchSchool(schoolName)).thenReturn(
//                searchSchoolResponse
//            )
//
//            val repositoryResult = schoolRepository.searchSchool(schoolName)
//            repositoryResult.collect {
//                assertEquals(searchSchoolResponse, it)
//            }
//        }
    }
}