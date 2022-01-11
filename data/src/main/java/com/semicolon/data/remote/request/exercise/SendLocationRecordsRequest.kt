package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName

data class SendLocationRecordsRequest(
    @SerializedName("location_list") val locationList: List<Location>
) {
    data class Location(
        @SerializedName("order") val order: Int,
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude") val longitude: String
    )
}