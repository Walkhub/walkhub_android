package com.semicolon.domain.repository.images

import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface ImagesRepository {

    suspend fun postImages(images : List<MultipartBody.Part>) : Flow<List<String>>
}