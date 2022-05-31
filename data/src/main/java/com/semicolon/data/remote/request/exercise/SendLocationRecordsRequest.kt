package com.semicolon.data.remote.request.exercise

import com.google.gson.annotations.SerializedName
import com.semicolon.data.local.entity.exercise.LocationRecordEntity

data class SendLocationRecordsRequest(
    @SerializedName("location_list") val locationList: List<Location>
) {
    data class Location(
        @SerializedName("order") val order: Int,
        @SerializedName("latitude") val latitude: String,
        @SerializedName("longitude") val longitude: String
    )
}

fun List<LocationRecordEntity>.toRequest() = SendLocationRecordsRequest(
    locationList = mapIndexed { idx, location ->
        SendLocationRecordsRequest.Location(
            order = idx,
            latitude = location.latitude.toString(),
            longitude = location.longitude.toString()
        )
    }
)