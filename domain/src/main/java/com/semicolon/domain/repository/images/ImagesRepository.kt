package com.semicolon.domain.repository.images

import kotlinx.coroutines.flow.Flow
import java.io.File

interface ImagesRepository {

    suspend fun postImages(images : List<File>) : Flow<List<String>>
}