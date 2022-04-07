package com.semicolon.domain.usecase.school

import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.entity.school.SearchSchoolEntity
import com.semicolon.domain.repository.SchoolRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSchoolDetailUseCase @Inject constructor(
    private val schoolRepository: SchoolRepository
) : UseCase<Int, Flow<SchoolDetailEntity>>() {

    override suspend fun execute(data: Int): Flow<SchoolDetailEntity> =
        schoolRepository.fetchSchoolDetail(data)
}