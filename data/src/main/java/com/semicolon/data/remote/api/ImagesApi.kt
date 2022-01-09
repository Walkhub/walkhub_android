package com.semicolon.data.remote.api

import com.semicolon.data.remote.response.images.ImagesResponse
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ImagesApi {
    @Multipart
    @POST("/images")
    suspend fun postImages(
        @Part images: List<File>
    ): ImagesResponse

}