package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchSchoolUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<String, Flow<SearchSchoolEntity>>() {

    override suspend fun execute(data: String): Flow<SearchSchoolEntity> =
        rankRepository.searchSchool(data)
}