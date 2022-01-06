package com.semicolon.data.remote.response.users.inquiryownerbadges

import com.google.gson.annotations.SerializedName

data class InquiryOwnerBadgesResponse(
    @SerializedName("badge_list") val badgeList: List<Badge>
)