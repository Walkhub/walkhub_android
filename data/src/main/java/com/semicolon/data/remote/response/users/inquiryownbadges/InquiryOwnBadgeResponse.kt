package com.semicolon.data.remote.response.users.inquiryownbadges

import com.google.gson.annotations.SerializedName

data class InquiryOwnBadgeResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
)