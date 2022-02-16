package com.semicolon.data.datasource

import com.nhaarman.mockitokotlin2.mock
import com.semicolon.data.remote.api.BadgeApi
import com.semicolon.data.remote.datasource.RemoteBadgeDataSource
import com.semicolon.data.remote.datasource.RemoteBadgeDataSourceImpl

class RemoteBadgeDataSourceUnitTest {

    private val badgeApi = mock<BadgeApi>()

    private val remoteChallengeDatasource: RemoteBadgeDataSource =
        RemoteBadgeDataSourceImpl(badgeApi)

}