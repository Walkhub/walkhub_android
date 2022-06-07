package com.semicolon.walkhub.extensions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import dagger.hilt.android.qualifiers.ApplicationContext
import gun0912.tedimagepicker.builder.TedImagePicker.Companion.with
import gun0912.tedimagepicker.builder.TedRxImagePicker.Companion.with
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UrlConverter @Inject constructor(@ApplicationContext private val context: Context) {

    suspend fun convert(url: String): File? {
        return suspendCoroutine {
            Glide.with(context)
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
                        it.resume(null)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        it.resume(null)
                    }
                })
        }
    }
}