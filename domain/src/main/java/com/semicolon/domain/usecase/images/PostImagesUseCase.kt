package com.semicolon.domain.usecase.images

import android.provider.MediaStore
import com.semicolon.domain.repository.images.ImagesRepository
import com.semicolon.domain.usecase.UseCase
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class PostImagesUseCase @Inject constructor(
    private val postImagesRepository: ImagesRepository
): UseCase<List<MultipartBody.Part>, Flow<List<String>>>() {
    override suspend fun execute(data: List<MultipartBody.Part>): Flow<List<String>> =
        postImagesRepository.postImages(data)
}