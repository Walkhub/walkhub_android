package com.semicolon.walkhub.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.semicolon.domain.exception.ChangeFileSizeException
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

fun File.limitSize(): File {
    try {
        val option = BitmapFactory.Options().apply {
            inJustDecodeBounds = true
            inSampleSize = 6
        }

        var inputStream = FileInputStream(this)
        BitmapFactory.decodeStream(inputStream, null, option)
        inputStream.close()

        val requiredSize = 75

        var scale = 1
        while (option.outWidth / scale / 2 >= requiredSize && option.outHeight / scale / 2 >= requiredSize) {
            scale *= 2
        }

        val option2 = BitmapFactory.Options().apply {
            inSampleSize = scale
        }
        inputStream = FileInputStream(this)

        val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, option2)
        inputStream.close()

        this.createNewFile()
        val outStream = FileOutputStream(this)

        selectedBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outStream)

        return this
    } catch (e: Exception) {
        Log.e("finish", e.toString())
        throw ChangeFileSizeException()
    }
}