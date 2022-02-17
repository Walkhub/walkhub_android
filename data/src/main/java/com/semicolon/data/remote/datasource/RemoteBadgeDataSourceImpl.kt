package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.BadgeApi
import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse
import com.semicolon.data.remote.response.badge.toEntity
import com.semicolon.data.util.HttpHandler
import com.semicolon.domain.entity.badge.FetchMyBadgesEntity
import com.semicolon.domain.entity.badge.FetchNewBadgesEntity
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import javax.inject.Inject

class RemoteBadgeDataSourceImpl @Inject constructor(
    private val badgeApi: BadgeApi
) : RemoteBadgeDataSource {

    override suspend fun fetchUserBadges(userId: Int): FetchUserBadgesEntity =
        HttpHandler<FetchUserBadgesResponse>()
            .httpRequest { badgeApi.fetchUserBadges(userId) }
            .sendRequest().toEntity()

    override suspend fun fetchMyBadges(): FetchMyBadgesEntity =
        HttpHandler<FetchMyBadgesResponse>()
            .httpRequest { badgeApi.fetchMyBadges() }
            .sendRequest().toEntity()

    override suspend fun setBadge(badgeId: Int) =
        HttpHandler<Unit>()
            .httpRequest { badgeApi.setBadge(badgeId) }
            .sendRequest()

    override suspend fun fetchNewBadges(): FetchNewBadgesEntity =
        HttpHandler<FetchNewBadgesResponse>()
            .httpRequest { badgeApi.fetchNewBadges() }
            .sendRequest().toEntity()
}