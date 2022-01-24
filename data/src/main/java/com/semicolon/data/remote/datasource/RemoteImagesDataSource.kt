package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.response.image.ImagesResponse
import okhttp3.MultipartBody

interface RemoteImagesDataSource {

    suspend fun postImages(images : List<MultipartBody.Part>): ImagesResponse
}