package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.SchoolRankAndSearchEntity
import com.semicolon.domain.param.user.FetchSchoolRankAndSearchParam
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SchoolRankAndSearchUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<FetchSchoolRankAndSearchParam, Flow<SchoolRankAndSearchEntity>>() {

    override suspend fun execute(data: FetchSchoolRankAndSearchParam): Flow<SchoolRankAndSearchEntity> =
        rankRepository.fetchSchoolRankAndSearch(data)
}