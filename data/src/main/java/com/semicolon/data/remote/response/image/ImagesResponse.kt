package com.semicolon.data.remote.response.image

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("image_url") val imageUrl: List<String>
)