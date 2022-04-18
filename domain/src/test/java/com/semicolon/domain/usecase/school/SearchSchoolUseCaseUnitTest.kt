package com.semicolon.domain.usecase.school

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.semicolon.domain.entity.school.SearchSchoolEntity
import com.semicolon.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchSchoolUseCaseUnitTest {

    private val schoolRepository = mock<SchoolRepository>()

    private val searchSchoolUseCase = SearchSchoolUseCase(schoolRepository)

    @Test
    fun testSearchSchoolUseCase() {
        val schoolName = "my school"
        val schoolId = 1
        val image = "image"
        
        val searchSchoolResponse =
            SearchSchoolEntity(
                schoolList = listOf(
                    SearchSchoolEntity.SchoolInfo(
                        schoolId,
                        image,
                        schoolName
                    )
                )
            )

        runBlocking {
            whenever(schoolRepository.searchSchool(schoolName)).thenReturn(
                flow {
                    emit(searchSchoolResponse)
                }
            )

            val useCaseResult = searchSchoolUseCase.execute(schoolName)
            useCaseResult.collect {
                assertEquals(searchSchoolResponse,it)
            }
        }
    }
}