package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeListResponse(@SerializedName("challenge_list") val challengeList: List<ChallengeResponse>)