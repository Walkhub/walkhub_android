package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.OurSchoolUserRankEntity
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchOurSchoolUserRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<String, Flow<OurSchoolUserRankEntity>>() {

    override suspend fun execute(data: String): Flow<OurSchoolUserRankEntity> =
        rankRepository.fetchOurSchoolUserRank(data, data)
}