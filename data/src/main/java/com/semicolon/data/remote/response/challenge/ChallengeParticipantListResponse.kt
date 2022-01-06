package com.semicolon.data.remote.response.challenge

import com.google.gson.annotations.SerializedName

data class ChallengeParticipantListResponse(@SerializedName("participant_list") val participantList: List<ChallengeParticipantResponse>)