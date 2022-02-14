package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.BadgeApi
import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse
import com.semicolon.data.util.HttpHandler
import javax.inject.Inject

class RemoteBadgeDataSourceImpl @Inject constructor(
    private val badgeApi: BadgeApi
) : RemoteBadgeDataSource {

    override suspend fun fetchUserBadges(userId: Int): FetchUserBadgesResponse =
        HttpHandler<FetchUserBadgesResponse>()
            .httpRequest { badgeApi.fetchUserBadges(userId) }
            .sendRequest()

    override suspend fun fetchMyBadges(): FetchMyBadgesResponse =
        HttpHandler<FetchMyBadgesResponse>()
            .httpRequest { badgeApi.fetchMyBadges() }
            .sendRequest()

    override suspend fun setBadge(badgeId: Int) =
        HttpHandler<Unit>()
            .httpRequest { badgeApi.setBadge(badgeId) }
            .sendRequest()

    override suspend fun fetchNewBadges(): FetchNewBadgesResponse =
        HttpHandler<FetchNewBadgesResponse>()
            .httpRequest { badgeApi.fetchNewBadges() }
            .sendRequest()
}