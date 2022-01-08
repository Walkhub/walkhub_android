package com.semicolon.data.remote.response.images

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    val imagesUrl : List<ImageUrl>
) {
    data class ImageUrl(
        @SerializedName("image_url") val imagesUrl : String
    )
}