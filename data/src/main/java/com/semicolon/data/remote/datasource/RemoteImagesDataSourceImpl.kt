package com.semicolon.data.remote.datasource

import com.semicolon.data.remote.api.ImagesApi
import com.semicolon.data.remote.response.image.ImagesResponse
import com.semicolon.data.util.HttpHandler
import okhttp3.MultipartBody
import javax.inject.Inject

class RemoteImagesDataSourceImpl @Inject constructor(
    private val imagesApi : ImagesApi
): RemoteImagesDataSource {
    override suspend fun postImages(images: List<MultipartBody.Part>): ImagesResponse =
        HttpHandler<ImagesResponse>()
            .httpRequest { imagesApi.postImages(images) }
            .sendRequest()
}