package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.FetchMySchoolRankEntity
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchMySchoolRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<String, Flow<FetchMySchoolRankEntity>>() {

    override suspend fun execute(data: String): Flow<FetchMySchoolRankEntity> =
        rankRepository.fetchMySchoolRank(data)
}