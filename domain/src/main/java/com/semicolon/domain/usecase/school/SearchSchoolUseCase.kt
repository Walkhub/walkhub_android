package com.semicolon.domain.usecase.school

import com.semicolon.domain.entity.school.SearchSchoolEntity
import com.semicolon.domain.repository.SchoolRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSchoolUseCase @Inject constructor(
    private val searchSchoolRepository: SchoolRepository
) : UseCase<String, Flow<List<SearchSchoolEntity>>>() {
    override suspend fun execute(data: String): Flow<List<SearchSchoolEntity>> =
        searchSchoolRepository.searchSchool(data)
}