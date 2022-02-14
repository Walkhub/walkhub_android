package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.badge.FetchMyBadgesResponse
import com.semicolon.data.remote.response.badge.FetchNewBadgesResponse
import com.semicolon.data.remote.response.badge.FetchUserBadgesResponse
import com.semicolon.domain.entity.badge.FetchUserBadgesEntity
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface BadgeApi {

    @GET("badges/{user-id}")
    suspend fun fetchUserBadges(
        @Path("user-id") userId: Int
    ): FetchUserBadgesResponse

    @GET("badges")
    suspend fun fetchMyBadges() : FetchMyBadgesResponse

    @PUT("badges/{badge-id}")
    suspend fun setBadge(
        @Path("badge-id") badgeId: Int
    )

    @GET("badges/new")
    suspend fun fetchNewBadges() : FetchNewBadgesResponse
}