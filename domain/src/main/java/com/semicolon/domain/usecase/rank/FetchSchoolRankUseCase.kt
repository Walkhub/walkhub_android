package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.SchoolRankEntity
import com.semicolon.domain.enums.DateType
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchSchoolRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<DateType, Flow<SchoolRankEntity>>() {

    override suspend fun execute(data: DateType): Flow<SchoolRankEntity> =
        rankRepository.fetchSchoolRank(data)
}