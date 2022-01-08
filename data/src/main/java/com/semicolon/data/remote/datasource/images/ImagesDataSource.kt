package com.semicolon.data.remote.datasource.images

import com.semicolon.data.remote.response.images.ImagesResponse
import okhttp3.MultipartBody

interface ImagesDataSource {

    suspend fun postImages(images : List<MultipartBody.Part>): ImagesResponse
}