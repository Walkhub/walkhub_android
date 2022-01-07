package com.semicolon.data.remote.api

import android.media.Image
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImagesApi {

    @Multipart
    @POST("/images")
    suspend fun postImages(
         @Part images : List<MultipartBody.Part>
    )

}