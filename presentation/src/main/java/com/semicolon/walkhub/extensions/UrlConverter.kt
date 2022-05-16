package com.semicolon.walkhub.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.semicolon.domain.exception.ImageConverterException
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class UrlConverter @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun convert(url: String): File {
        return suspendCoroutine {
            GlideApp.with(context)
                .asBitmap()
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        val file = File.createTempFile("JPEG_", ".jpg", context.cacheDir)
                        FileOutputStream(file)
                            .use { out -> resource.compress(Bitmap.CompressFormat.JPEG, 100, out) }
                        file.deleteOnExit()
                        it.resume(file)
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        it.resumeWithException(ImageConverterException())
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        it.resumeWithException(ImageConverterException())
                    }
                })
        }
    }
}