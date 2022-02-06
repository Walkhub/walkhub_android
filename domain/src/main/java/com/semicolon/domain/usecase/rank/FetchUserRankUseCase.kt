package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.UserRankEntity
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserRankUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<String, Flow<UserRankEntity>>() {

    override suspend fun execute(data: String): Flow<UserRankEntity> =
        rankRepository.fetchUserRank(data,data,data)
}