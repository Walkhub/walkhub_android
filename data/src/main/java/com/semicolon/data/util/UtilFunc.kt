package com.semicolon.data.util

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


fun File.toMultipart(): MultipartBody.Part {
    val fileBody = RequestBody.create(MediaType.parse("image/jpeg"), this)
    return MultipartBody.Part.createFormData("images", this.name, fileBody)
}

fun List<File>.toMultipartList(): List<MultipartBody.Part> =
    this.map { it.toMultipart() }