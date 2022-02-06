package com.semicolon.domain.usecase.rank

import com.semicolon.domain.entity.rank.SearchSchoolEntity
import com.semicolon.domain.entity.rank.SearchUserEntity
import com.semicolon.domain.param.rank.SearchUserParam
import com.semicolon.domain.repository.RankRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val rankRepository: RankRepository
) : UseCase<SearchUserParam, Flow<SearchUserEntity>>() {

    override suspend fun execute(data: SearchUserParam): Flow<SearchUserEntity> =
        rankRepository.searchUser(data)
}